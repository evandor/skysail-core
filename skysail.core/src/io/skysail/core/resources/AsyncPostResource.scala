package io.skysail.core.resources

import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef
import scala.reflect.runtime.universe._

abstract class AsyncPostResource[T: TypeTag] extends Resource[T] with ActorContextAware {

  def get(sendBackTo: ActorRef): Unit

  def post(sendBackTo: ActorRef): Unit

}