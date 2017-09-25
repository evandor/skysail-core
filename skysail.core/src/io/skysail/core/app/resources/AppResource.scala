package io.skysail.core.app.resources

import akka.actor.ActorRef
import io.skysail.core.resources.AsyncEntityResource
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.concurrent.Future
import scala.reflect.ClassTag

class AppResource extends AsyncEntityResource[String] {

  //override def get() = { "Gi".asInstanceOf[String] }
//  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//  }
   def get() = ???

  def get(sendBackTo: ActorRef, cmd: ProcessCommand): Unit = {
     ???
   }
}