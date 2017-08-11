package io.skysail.core.app.resources

import akka.actor.ActorRef
import io.skysail.core.akka.actors.EntityResource

import scala.concurrent.Future
import scala.reflect.ClassTag

class AppResource extends EntityResource[String] {

  //override def get() = { "Gi".asInstanceOf[String] }
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
  }
}