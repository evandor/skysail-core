package io.skysail.core.app.resources

import akka.actor.{ActorRef, ActorSystem}
import akka.actor.ActorContext

trait ActorContextAware {
  var actorContext: ActorContext
  def setActorContext(context: ActorContext) = this.actorContext = context
  def getSender() = this.actorContext.sender
  def getSystem() = this.actorContext.system
}
