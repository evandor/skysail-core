package io.skysail.core.resources

import scala.reflect.ClassTag
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef

abstract class AsyncListResource[T: ClassTag] extends Resource[List[T]] with ActorContextAware {

  def get(sendBackTo: ActorRef): Unit

}