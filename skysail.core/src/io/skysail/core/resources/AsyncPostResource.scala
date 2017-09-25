package io.skysail.core.resources

import akka.actor.ActorRef

import scala.reflect.runtime.universe._
import io.skysail.core.app.resources.PostSupport
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

abstract class AsyncPostResource[T: TypeTag] extends AsyncResource[T] with PostSupport {

  def get(sendBackTo: ActorRef, cmd: ProcessCommand): Unit

  def post(sendBackTo: ActorRef): Unit

}