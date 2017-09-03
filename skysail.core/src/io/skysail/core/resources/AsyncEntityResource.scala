package io.skysail.core.resources

import io.skysail.core.akka._
import scala.reflect.ClassTag
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef

abstract class AsyncEntityResource[T: ClassTag] extends Resource[T] with ActorContextAware {
  
  def get(sendBackTo: ActorRef): Unit

}