package io.skysail.core.akka

import scala.concurrent.Future

import akka.actor.{ ActorSystem, Actor, Props }
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
import domino.DominoActivator

class Activator3 extends DominoActivator {

  def configure(osgiContext: BundleContext, system: ActorSystem) {
    whenBundleActive {
      onStart {
        println("Bundle started")
      }

      onStop {
        println("Bundle stopped")
      }
    }
  }

}

