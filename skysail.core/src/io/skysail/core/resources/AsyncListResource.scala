package io.skysail.core.resources

import scala.reflect.runtime.universe._
import akka.actor.ActorRef
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

abstract class AsyncListResource[T: TypeTag] extends AsyncResource[List[T]] {

  def get(sendBackTo: ActorRef, cmd: ProcessCommand): Unit

}