package io.skysail.core.akka

import akka.actor.{ActorLogging, Actor}

case class Greeting(message: String)

class Greeter extends Actor with ActorLogging {
  def receive = {
    case Greeting(message) => log.info("Hello {}!", message)
  }
}

class SilentActor extends Actor {
  def receive = {
    case msg =>
  }
}