package io.skysail.core.resources

import scala.reflect.runtime.universe._
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef

abstract class AsyncListResource[T: TypeTag] extends AsyncResource[List[T]] {

  def get(sendBackTo: ActorRef): Unit

//  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
//    def getType(): Type = typeOf[T]
//  }
}