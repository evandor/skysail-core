package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import scala.reflect.ClassTag

class AssetsController extends ResourceController[Any] {

  
  override val chainRoot = (
    classOf[RequestProcessingActor[Any]] ==>
    classOf[Timer] ==>
    classOf[AssetRetriever]).build(null)

  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
    ???
  }

}