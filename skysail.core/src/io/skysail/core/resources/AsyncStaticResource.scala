package io.skysail.core.resources

import io.skysail.core.akka._
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef

abstract class AsyncStaticResource extends Resource[Any] with ActorContextAware {

  def get(sendBackTo: ActorRef): Unit

}