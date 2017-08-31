package io.skysail.core.app.resources

import akka.actor.{ActorRef, ActorSystem}

trait ActorSystemAware {
  var sender: ActorRef
  var as: ActorSystem
  def setActorSystem(as: ActorSystem) = this.as = as
  def setSender(sender: ActorRef) = this.sender = sender
}
