package io.skysail.core.akka

import scala.concurrent.Future

import akka.actor.{ ActorSystem , Actor, Props }
import akka.event.Logging
import akka.util.Timeout

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer

import com.typesafe.config.{ Config, ConfigFactory }
import akka.osgi.ActorSystemActivator
import org.osgi.framework.BundleContext
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration

class Activator extends ActorSystemActivator {

  def configure(context: BundleContext, system: ActorSystem) {
    // optionally register the ActorSystem in the OSGi Service Registry
    registerService(context, system)

    //val someActor = system.actorOf(Props[SomeActor], name = "someName")
    //someActor ! SomeMessage

    implicit val ec = system.dispatcher

    val api = new RestApi(system, requestTimeout()).routes

   // implicit val actorRefFactory = ActorRefFactory()
    implicit val acctorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer()
    val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(api, "localhost", 5001)

    val log = Logging(system.eventStream, "go-ticks")
    bindingFuture.map { serverBinding =>
      log.info(s"RestApi bound to ${serverBinding.localAddress} ")
    }.onFailure {
      case ex: Exception =>
        log.error(ex, "Failed to bind to {}:{}!", "localhost", 5001)
        system.terminate()
    }
  }

  def requestTimeout(): Timeout = {
    val t = "10 s" //config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}

