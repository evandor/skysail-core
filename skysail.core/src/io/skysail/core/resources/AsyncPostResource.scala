package io.skysail.core.resources

import akka.actor.ActorSystem
import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.resources.PostSupport

import scala.reflect.runtime.universe._

abstract class AsyncPostResource[T: TypeTag] extends AsyncResource[T] with PostSupport {

  def get(requestEvent: RequestEvent): Unit

  def post(requestEvent: RequestEvent): Unit

}