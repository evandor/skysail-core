package io.skysail.core.resources

import io.skysail.core.akka._

import scala.reflect.ClassTag
import akka.actor.ActorRef
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.reflect.runtime.universe._

abstract class AsyncEntityResource[T: TypeTag] extends AsyncResource[T] {
  
  def get(sendBackTo: ActorRef, cmd: ProcessCommand): Unit

}