package io.skysail.core.app.resources

import akka.actor.ActorRef
import io.skysail.core.akka.actors.AsyncEntityResource

import scala.concurrent.Future
import scala.reflect.ClassTag

class AppResource extends AsyncEntityResource[String] {

  //override def get() = { "Gi".asInstanceOf[String] }
//  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//  }
   def get() = ???

  def get(sendBackTo: ActorRef): Unit = {
     ???
   }
}