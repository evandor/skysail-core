package io.skysail.core.app.resources

import akka.actor.ActorRef

import scala.concurrent.Future
import scala.reflect.ClassTag

class AkkaLoginResource[String] extends RedirectResource[String] {
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {

    ???
  }

  def redirectTo(): String = {
    ???
  }
}