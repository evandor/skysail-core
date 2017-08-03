package io.skysail.core.server

import akka.osgi.ActorSystemActivator
import org.osgi.framework.BundleContext
import io.skysail.core.app.ApplicationRoutesProvider
import akka.actor.{ActorRef, ActorSystem, PoisonPill, Props}
import akka.http.scaladsl.server.Route

import scala.concurrent.Future
import domino.service_watching.ServiceWatcherEvent.{AddingService, ModifiedService, RemovedService}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import domino.DominoActivator
import domino.capsule.Capsule
import org.slf4j.LoggerFactory
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow

import scala.reflect.api.materializeTypeTag
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.akka.ResourceActor
import io.skysail.core.akka.PrivateMethodExposer
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.RequestContext
import akka.http.scaladsl.server.RouteResult
import io.skysail.core.server.AkkaServer._
import akka.pattern.ask
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.SkysailApplication.CreateApplicationActor
import io.skysail.core.app.resources.DefaultResource2
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

object AkkaServer {
  def getApplicationActorSelection(system: ActorSystem, name: String) = {
    val applicationActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName + "/" + name
    system.actorSelection(applicationActorPath)
  }
}

class AkkaServer extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  implicit var theSystem: ActorSystem = _
  var routes = scala.collection.mutable.ListBuffer[Route]()
  var futureBinding: Future[Http.ServerBinding] = _
  var applicationsActor: ActorRef =
    _

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
    watchServices[ApplicationRoutesProvider] {
      case AddingService(service, context) => addService(service)
      case ModifiedService(service, _) => log info s"Service '$service' modified"
      case RemovedService(service, _) => removeService(service)
    }
  })

  private def addService(s: ApplicationRoutesProvider) = {
    if (s == null) {
      log warn "service null"
    } else {

      val appsActor = SkysailApplication.getApplicationsActor(theSystem)
      implicit val askTimeout: Timeout = 3.seconds
      appsActor ! CreateApplicationActor(s.getClass.asInstanceOf[Class[SkysailApplication]])

      log info "========================================="
      log info s"Adding routes from ${s.getClass.getName}"
      log info "========================================="
      val routes2 = s.routes2()

      val r = routes2.map { prt => createRoute(prt._1, prt._2, s.getClass) }.toList
      routes ++= r //s.routes()
      restartServer(routes.toList)
    }
  }

  private def removeService(s: ApplicationRoutesProvider) = {
    val appsActor = SkysailApplication.getApplicationsActor(theSystem)
    implicit val askTimeout: Timeout = 3.seconds
    // appsActor ! DeleteApplicationActor(s.)

    //log info s"Removing routes ${s.routes()} not supplied no more from ${s.getClass.getName}"
    //routes --= s.routes()
    restartServer(routes.toList)
  }

  private def restartServer(arg: List[Route]) = {
    implicit val materializer = ActorMaterializer()
    if (futureBinding != null) {
      implicit val executionContext = theSystem.dispatcher
      futureBinding.flatMap(_.unbind()).onComplete { _ => futureBinding = startServer(arg) }
    } else {
      futureBinding = startServer(arg)
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

  protected def createRoute(appPath: PathMatcher[Unit], cls: Class[_ <: ResourceActor[_]], c: Class[_]) = {
    path(appPath) {
      get {
        extractRequestContext {
          ctx =>
            {
              implicit val askTimeout: Timeout = 3.seconds

              val res = new PrivateMethodExposer(theSystem)('printTree)()
              println(res)
              log info s"actorOf ${cls}"
              val appActorSelection = getApplicationActorSelection(theSystem, c.getName)
              log info "appActorSelection: " + appActorSelection
              val t = (appActorSelection ? (ctx, cls)).mapTo[HttpResponse]
              onSuccess(t) {
                x => complete(x)
              }
            }
        }
      }
    }
  }

}