package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._

abstract class EntityResourceController[T] extends ResourceController[T] {

  override val chainRoot = (
    classOf[RequestProcessingActor[T]] ==>
      classOf[Timer] ==>
      classOf[EntityRetriever[T]] ==>
      classOf[AddLinkheaders]).build()

//  override val chainRoot = (
//    classOf[RequestProcessingActor[_]] ==>
//    classOf[Timer] ==>
//    classOf[ListRetriever[T]] ==>
//    classOf[AddLinkheaders] ==>
//    classOf[Redirector]).build()

}