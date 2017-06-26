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

class Activator2 extends ActorSystemActivator {

  def configure(osgiContext: BundleContext, system: ActorSystem) {
    val log = system.log

    log info "Registering Actor System as Service."
    registerService(osgiContext, system)

    log info s"ActorSystem [${system.name}] initialized."
  }

 
}

