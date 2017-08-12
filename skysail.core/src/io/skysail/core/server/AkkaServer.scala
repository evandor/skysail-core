package io.skysail.core.server

import akka.osgi.ActorSystemActivator
import org.osgi.framework.BundleContext
import io.skysail.core.app.ApplicationInfoProvider
import akka.actor.{ ActorRef, ActorSystem, PoisonPill, Props }
import akka.http.scaladsl.server.Route

import scala.concurrent.Future
import domino.service_watching.ServiceWatcherEvent.{ AddingService, ModifiedService, RemovedService }
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import domino.DominoActivator
import domino.capsule.Capsule
import org.slf4j.LoggerFactory
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow

import scala.reflect.api.materializeTypeTag
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.akka.{ PrivateMethodExposer, ResourceActor, ResponseEvent }
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.RequestContext
import akka.http.scaladsl.server.RouteResult
import io.skysail.core.server.AkkaServer._
import akka.pattern.ask
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.SkysailApplication.{ CreateApplicationActor, DeleteApplicationActor }
import io.skysail.core.app.resources.DefaultResource2
import java.util.concurrent.atomic.AtomicInteger

import akka.http.scaladsl.server.ContentNegotiator.Alternative.ContentType
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.headers.`Content-Type`
import akka.http.scaladsl.model.ContentTypes._

object AkkaServer {
  def getApplicationActorSelection(system: ActorSystem, name: String) = {
    val applicationActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName + "/" + name
    system.actorSelection(applicationActorPath)
  }
}

class AkkaServer extends DominoActivator with SprayJsonSupport {

  private var log = LoggerFactory.getLogger(this.getClass)

  implicit var theSystem: ActorSystem = _
  var routes = scala.collection.mutable.ListBuffer[Route]()
  var futureBinding: Future[Http.ServerBinding] = _
  var applicationsActor: ActorRef =
    _

  val counter = new AtomicInteger(0)

  private class AkkaCapsule(bundleContext: BundleContext) extends ActorSystemActivator with Capsule {

    override def start(): Unit = start(bundleContext)

    override def stop(): Unit = stop(bundleContext)

    def configure(osgiContext: BundleContext, system: ActorSystem): Unit = {
      log info "Registering Actor System as Service."
      //registerService(osgiContext, system)
      log info s"ActorSystem [${system.name}] initialized."
      theSystem = system
      // counterActor = system.actorOf(Props[CounterActor], "Counter")
      applicationsActor = system.actorOf(Props[ApplicationsActor], classOf[ApplicationsActor].getSimpleName)
      log info s"created ApplicationsActor with path ${applicationsActor.path}"
    }

    override def getActorSystemName(context: BundleContext): String = "SkysailActorSystem"
  }

  whenBundleActive({
    addCapsule(new AkkaCapsule(bundleContext))
    watchServices[ApplicationInfoProvider] {
      case AddingService(service, context) => addService(service)
      case ModifiedService(service, _) => log info s"Service '$service' modified"
      case RemovedService(service, _) => removeService(service)
    }
  })

  private def addService(appInfoProvider: ApplicationInfoProvider) = {
    implicit val askTimeout: Timeout = 3.seconds
    //    if (appInfoProvider == null) {
    //      log warn "service null"
    //    } else {

    val appsActor = SkysailApplication.getApplicationsActor(theSystem)
    appsActor ! CreateApplicationActor(appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]], appInfoProvider.appModel())

    log info "========================================="
    log info s"Adding routes from ${appInfoProvider.getClass.getName}"
    log info "========================================="

    val routesFromProvider = appInfoProvider.routes()
    routes ++= routesFromProvider.map { prt => createRoute(prt._1, prt._2, appInfoProvider.getClass) }.toList
    restartServer(routes.toList)
    //    }
  }

  private def removeService(appInfoProvider: ApplicationInfoProvider) = {
    val appsActor = SkysailApplication.getApplicationsActor(theSystem)
    appsActor ! DeleteApplicationActor(appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]])

    log info "========================================="
    log info s"Removing routes from ${appInfoProvider.getClass.getName}"
    log info "========================================="
    //log info s"Removing routes ${s.routes()} not supplied no more from ${s.getClass.getName}"
    //routes --= s.routes()
    // TODO need to fix that, routes are not removed
    val routesFromProvider = appInfoProvider.routes()
    routes --= routesFromProvider.map { prt => createRoute(prt._1, prt._2, appInfoProvider.getClass) }.toList

    restartServer(routes.toList)
  }

  private def restartServer(routes: List[Route]) = {
    implicit val materializer = ActorMaterializer()
    log info s"(re)starting server @ port 8080 with #${routes.size} routes."
    if (futureBinding != null) {
      implicit val executionContext = theSystem.dispatcher
      futureBinding.flatMap(_.unbind()).onComplete { _ => futureBinding = startServer(routes) }
    } else {
      futureBinding = startServer(routes)
    }
  }

  private def startServer(arg: List[Route]) = {
    implicit val materializer = ActorMaterializer()
    //println(arg)
    arg.size match {
      case 0 =>
        log warn "Akka HTTP Server not started as no routes are defined"; null
      case 1 => Http(theSystem).bindAndHandle(arg(0), "localhost", 8080)
      case _ => Http(theSystem).bindAndHandle(arg.reduce((a, b) => a ~ b), "localhost", 8080)
    }
  }

  protected def createRoute(appPath: PathMatcher[Unit], cls: Class[_ <: ResourceActor[_]], c: Class[_]): Route = {
    val appSelector = getApplicationActorSelection(theSystem, c.getName)
    //    new MyJsonService().route(appSelector, cls) ~
    //    new JsonService().route(appPath / "broken", appSelector, cls) ~
    //    path("hello") {
    //      get {
    //        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
    //      }
    //    } ~
    //      path("hello2") {
    //        get {
    //          complete(HttpResponse(entity = HttpEntity(ContentType(MediaTypes.`application/json`), """{"id":"1"}""")))
    //        }
    //      } ~
    path(appPath) {
      get {
        extractRequestContext {
          ctx =>
            {
              log info s"executing route#${counter.incrementAndGet()}"
              implicit val askTimeout: Timeout = 3.seconds
              //println(new PrivateMethodExposer(theSystem)('printTree)())
              val appActorSelection = getApplicationActorSelection(theSystem, c.getName)
              val t = (appActorSelection ? (ctx, cls)).mapTo[ResponseEvent[_]]
              onSuccess(t) { x => complete(x.httpResponse) }
            }
        }
      }
    }
  }

}