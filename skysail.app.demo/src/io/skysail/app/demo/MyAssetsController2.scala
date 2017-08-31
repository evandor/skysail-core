package io.skysail.app.demo

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import scala.reflect.ClassTag
import io.skysail.core.akka.actors.Timer

class MyAssetsController2 extends ResourceController[Any] {

  override val chainRoot = (
    classOf[RequestProcessingActor[Any]] ==>
    classOf[Timer] ==>
    classOf[MyAssetRetriever2]).build(null)

  override def get() = ???

}