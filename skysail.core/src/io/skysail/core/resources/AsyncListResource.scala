package io.skysail.core.resources

import scala.reflect.runtime.universe._
import akka.actor.ActorRef

abstract class AsyncListResource[T: TypeTag] extends AsyncResource[List[T]] {

  def get(sendBackTo: ActorRef): Unit

}