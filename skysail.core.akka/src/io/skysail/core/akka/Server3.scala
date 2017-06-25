package io.skysail.core.akka

import akka.event.LogSource
import akka.osgi.ActorSystemActivator
import com.typesafe.config.Config
import domino.DominoActivator
import domino.capsule.Capsule
import org.osgi.framework.BundleContext
import io.skysail.core.app.ApplicationRoutesProvider
import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import scala.concurrent.Future
import domino.service_watching.ServiceWatcherEvent._

class Server3 extends DominoActivator {

  implicit var theSystem: ActorSystem = _
  
  var routes = scala.collection.mutable.ListBuffer[Route]()

  var futureBinding: Future[Http.ServerBinding] =
    _

  private class AkkaCapsule(bundleContext: BundleContext) extends ActorSystemActivator with Capsule {

    override def start(): Unit = start(bundleContext)
    override def stop(): Unit = stop(bundleContext)

    def configure(osgiContext: BundleContext, system: ActorSystem): Unit = {
      val log = system.log
      log info "Registering Actor System as Service."
      registerService(osgiContext, system)
      log info s"ActorSystem [${system.name}] initialized."
      theSystem = system
    }

    override def getActorSystemName(context: BundleContext): String = "SkysailActorSystem"
  }

  whenBundleActive {
    addCapsule(new AkkaCapsule(bundleContext))
//    services[ApplicationRoutesProvider] foreach { arp =>
//      routes ++= arp.routes()
//      restartServer(arp.routes())
//    }
    
    watchServices[ApplicationRoutesProvider] {
      case AddingService(s, context) =>
        println("Adding service " + s)
        val serviceRef = context.ref
        val serviceTracker = context.tracker
        routes ++= s.routes()
        restartServer(routes.toList)
      case ModifiedService(s, _) =>
        println("Service modified")
      case RemovedService(s, _) =>
        println("Service removed")
        routes --= s.routes()
        restartServer(routes.toList)
    }
  }

  def restartServer(arg: List[Route]) = {
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
//    arg.reduce((a,b) => a ~ b)
    Http(theSystem).bindAndHandle(arg(0), "localhost", 8080)
  }
}