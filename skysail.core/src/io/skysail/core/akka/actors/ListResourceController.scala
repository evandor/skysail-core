package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel

abstract class ListResourceController[T] extends ResourceController[List[T]] {

  override val chainRoot = (
    classOf[RequestProcessingActor[T]] ==>
    classOf[Timer] ==>
    classOf[ListRetriever[T]] ==>
    classOf[AddLinkheaders]).build()

}