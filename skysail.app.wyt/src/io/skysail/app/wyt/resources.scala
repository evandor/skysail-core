package io.skysail.app.wyt;

import akka.actor.ActorRef
import io.skysail.core.akka.actors._

import scala.concurrent.Future
import scala.reflect.ClassTag
import io.skysail.core.resources.AsyncListResource

class PactsResource extends AsyncListResource[Pact] {
  val pactsService = new PactsService()
  //override def get(): List[Pact] = pactsService.getPacts().toList
   protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]) {
    sender ! pactsService.getPacts().toList
  }

   def get() = ???

  def get(sendBackTo: ActorRef): Unit = {
    ???
  }
}

