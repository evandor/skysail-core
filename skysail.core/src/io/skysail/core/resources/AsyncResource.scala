package io.skysail.core.resources

import scala.reflect.runtime.universe._
import akka.actor.ActorRef
import io.skysail.core.akka.RequestEvent
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

abstract class AsyncResource[T: TypeTag] extends Resource[List[T]] with ActorContextAware {

  def get(requestEvent: RequestEvent): Unit

  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }

}