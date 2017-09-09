package io.skysail.core.resources

import io.skysail.core.akka._
import scala.reflect.ClassTag
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef
import scala.reflect.runtime.universe._

abstract class AsyncEntityResource[T: TypeTag] extends Resource[T] with ActorContextAware {
  
  def get(sendBackTo: ActorRef): Unit

}