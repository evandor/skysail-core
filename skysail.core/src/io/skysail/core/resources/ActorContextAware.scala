package io.skysail.core.resources

import akka.actor.ActorContext

trait ActorContextAware {
  var actorContext: ActorContext = null
  def setActorContext(context: ActorContext) = this.actorContext = context
  def getSender() = this.actorContext.sender
  def getSystem() = this.actorContext.system
}
