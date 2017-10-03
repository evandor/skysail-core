package io.skysail.app.wyt;

import akka.actor.ActorRef
import io.skysail.core.akka.RequestEvent
import io.skysail.core.resources.AsyncListResource

import scala.reflect.ClassTag

class PactsResource extends AsyncListResource[Pact] {
  val pactsService = new PactsService()
  //override def get(): List[Pact] = pactsService.getPacts().toList
   protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]) {
    sender ! pactsService.getPacts().toList
  }

   def get() = ???

  def get(requestEvent: RequestEvent): Unit = {
    ???
  }
}

