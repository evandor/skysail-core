package io.skysail.core.app.resources

import akka.actor.ActorRef
import io.skysail.core.akka.actors.EntityResourceController

import scala.concurrent.Future
import scala.reflect.ClassTag

class AppResource extends EntityResourceController[String] {

  //override def get() = { "Gi".asInstanceOf[String] }
//  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//  }
  override def get() = ???
}