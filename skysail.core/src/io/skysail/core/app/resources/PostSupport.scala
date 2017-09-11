package io.skysail.core.app.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.actor.ActorContext

trait PostSupport {
  def post(sendBackTo: ActorRef): Unit
}
