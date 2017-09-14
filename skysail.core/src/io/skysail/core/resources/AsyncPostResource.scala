package io.skysail.core.resources

import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef
import scala.reflect.runtime.universe._
import io.skysail.core.app.resources.PostSupport

abstract class AsyncPostResource[T: TypeTag] extends AsyncResource[T] with PostSupport {

  def get(sendBackTo: ActorRef): Unit

  def post(sendBackTo: ActorRef): Unit

}