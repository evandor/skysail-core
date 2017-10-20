package io.skysail.core.server

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow
import akka.osgi.ActorSystemActivator
import akka.stream.ActorMaterializer
import akka.util.Timeout
import domino.DominoActivator
import domino.bundle_watching.BundleWatcherEvent.{AddingBundle, ModifiedBundle, RemovedBundle}
import domino.capsule.Capsule
import domino.service_watching.ServiceWatcherContext
import domino.service_watching.ServiceWatcherEvent.{AddingService, ModifiedService, RemovedService}
import io.skysail.api.security.AuthenticationService
import io.skysail.core.Constants
import io.skysail.core.app.SkysailApplication.CreateApplicationActor
import io.skysail.core.app.{ApplicationProvider, SkysailApplication, SkysailRootApplication}
import io.skysail.core.server.actors.{ApplicationsActor, BundlesActor}
import io.skysail.core.server.routes.RoutesTracker
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

case class ServerConfig(val port: Integer, val binding: String)

class AkkaServer extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  implicit var actorSystem: ActorSystem = _
  var futureBinding: Future[Http.ServerBinding] = _
  var applicationsActor: ActorRef = _
  var bundlesActor: ActorRef = _

  val defaultPort = 8080
  val defaultBinding = "localhost"
  val defaultAuthentication = "HTTP_BASIC"
  var serverConfig = new ServerConfig(defaultPort, defaultBinding)

  var routesTracker: RoutesTracker = null

  private class AkkaCapsule(bundleContext: BundleContext) extends ActorSystemActivator with Capsule {

    override def start(): Unit = start(bundleContext)

    override def stop(): Unit = stop(bundleContext)

    def configure(osgiContext: BundleContext, system: ActorSystem): Unit = {
      log info "Registering Actor System as Service."
      //registerService(osgiContext, system)
      log info s"ActorSystem [${system.name}] initialized."
      actorSystem = system
      applicationsActor = system.actorOf(Props[ApplicationsActor], Constants.APPLICATIONS_ACTOR_NAME)
      log info s"created ApplicationsActor with path ${applicationsActor.path}"

      bundlesActor = system.actorOf(Props(new BundlesActor(bundleContext)), Constants.BUNDLES_ACTOR_NAME)
      log info s"created BundlesActor with path ${bundlesActor.path}"

      //routesTracker = new RoutesTracker(system, serverConfig.authentication)
    }

    override def getActorSystemName(context: BundleContext): String = "SkysailActorSystem"
  }

  whenBundleActive({
    addCapsule(new AkkaCapsule(bundleContext))

    //whenServicePresent[DbService] { s =>
      val app = new SkysailRootApplication()
      // app.dbService = s
      app.providesService[ApplicationProvider]
    //}

    watchServices[ApplicationProvider] {
      case AddingService(service, context) => addApplicationProvider(service, context)
      case ModifiedService(service, context) => log info s"Service '$service' modified"; addApplicationProvider(service, context)
      case RemovedService(service, _) => removeApplicationProvider(service)
    }

    watchServices[AuthenticationService] {
      case AddingService(service, context) => routesTracker.setAuthentication(service)
      case ModifiedService(service, _) => //log info s"Service '$service' modified"
      case RemovedService(service, _) => removeAuthenticationService(service)
    }

    watchBundles {
      case AddingBundle(b, context) => bundlesActor ! BundlesActor.CreateBundleActor(b)
      case ModifiedBundle(b, _) => //log info s"Bundle ${b.getSymbolicName} modified"
      case RemovedBundle(b, _) => log info s"Bundle ${b.getSymbolicName} removed"
    }

    whenConfigurationActive("server") { conf =>
      log info s"received configuration for 'server': ${conf}"
      val port = Integer.parseInt(conf.getOrElse("port", defaultPort.toString).asInstanceOf[String])
      var binding = conf.getOrElse("binding", defaultBinding).asInstanceOf[String]
      //var authentication = conf.getOrElse("authentication", defaultAuthentication).asInstanceOf[String]
      serverConfig = ServerConfig(port, binding)
      routesTracker = new RoutesTracker(actorSystem)
    }

  })

  private def addApplicationProvider(appInfoProvider: ApplicationProvider, ctx: ServiceWatcherContext[_]): Unit = {
    log info s"adding ApplicationProvider: $appInfoProvider; $ctx"
    createApplicationActor(appInfoProvider)
    routesTracker.addRoutesFor(appInfoProvider)
    restartServer(routesTracker.routes())
  }

  private def removeApplicationProvider(appInfoProvider: ApplicationProvider) = {
    routesTracker.removeRoutesFrom(appInfoProvider)
    restartServer(routesTracker.routes)
  }

  private def removeAuthenticationService(authenticationService: AuthenticationService) = {
    routesTracker.setAuthentication(null)
  }

  private def startServer(arg: List[Route]) = {
    implicit val materializer = ActorMaterializer()
    log info s"(re)starting server with binding ${serverConfig.binding}:${serverConfig.port} with #${routesTracker.routes.size} routes."
    arg.size match {
      case 0 =>
        log warn "Akka HTTP Server not started as no routes are defined"; null
      case 1 => Http(actorSystem).bindAndHandle(arg(0), serverConfig.binding, serverConfig.port)
      case _ => Http(actorSystem).bindAndHandle(arg.reduce((a, b) => a ~ b), serverConfig.binding, serverConfig.port)
    }
  }

  private def restartServer(routes: List[Route]) = {
    implicit val materializer = ActorMaterializer()
    if (futureBinding != null) {
      implicit val executionContext = actorSystem.dispatcher
      futureBinding.flatMap(_.unbind()).onComplete { _ => futureBinding = startServer(routes) }
    } else {
      futureBinding = startServer(routes)
    }
  }

  private def createApplicationActor(appInfoProvider: ApplicationProvider) = {
    if (appInfoProvider == null) {
      log warn "provided ApplicationProvider was null!"
    } else {
      implicit val askTimeout: Timeout = 1.seconds
      val appsActor = SkysailApplication.getApplicationsActor(actorSystem)
      val appClass = appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]]
      val appModel = appInfoProvider.appModel()
      val application = appInfoProvider.application()
      val optionalBundleContext = appInfoProvider.getBundleContext()

      appsActor ! CreateApplicationActor(appClass, appModel, application, optionalBundleContext)
    }
  }
}