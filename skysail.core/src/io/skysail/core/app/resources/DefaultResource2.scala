package io.skysail.core.app.resources

import akka.actor.ActorRef
import io.skysail.core.akka.actors._

import scala.concurrent.Future
import scala.reflect.ClassTag


class DefaultResource2 extends ListResourceController[String] {  
//  override def get(): List[String] = List("hi", "there")
override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
}
}
