package io.skysail.core.server

import akka.osgi.ActorSystemActivator
import org.osgi.framework.BundleContext
import io.skysail.core.app.ApplicationRoutesProvider
import akka.actor.ActorSystem
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
import io.skysail.core.akka.actors.CounterActor
import akka.actor.Props
import akka.actor.ActorRef

class AkkaServer extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  implicit var theSystem: ActorSystem = _
  var routes = scala.collection.mutable.ListBuffer[Route]()
  var futureBinding: Future[Http.ServerBinding] = _
  var applicationsActor: ActorRef = _

  private class AkkaCapsule(bundleContext: BundleContext) extends ActorSystemActivator with Capsule {

    override def start(): Unit = start(bundleContext)
    override def stop(): Unit = stop(bundleContext)

    def configure(osgiContext: BundleContext, system: ActorSystem): Unit = {
      log info "Registering Actor System as Service."
      registerService(osgiContext, system)
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
      case AddingService(s, context) => addService(s)
      case ModifiedService(s, _) => log info s"Service '$s' modified"
      case RemovedService(s, _) => removeService(s)
    }
  })
  
//  whenBundleStopped {
//    
//  }

  private def addService(s: ApplicationRoutesProvider) = {
    if (s == null) {
      log warn "service null"
    } else {
      log info s"Adding routes from ${s.getClass.getName}"
      routes ++= s.routes()
      restartServer(routes.toList)
    }
  }

  private def removeService(s: ApplicationRoutesProvider) = {
    log info s"Removing routes ${s.routes()} not supplied no more from ${s.getClass.getName}"
    routes --= s.routes()
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
    println(arg)
    arg.size match {
      case 0 =>
        log warn "Akka HTTP Server not started as no routes are defined"; null
      case 1 => Http(theSystem).bindAndHandle(arg(0), "localhost", 8080)
      case _ => Http(theSystem).bindAndHandle(arg.reduce((a, b) => a ~ b), "localhost", 8080)
    }
  }
}