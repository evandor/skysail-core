package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._

abstract class EntityResource[T] extends ResourceActor[T] {

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    classOf[ListRetriever[T]] ==>
    classOf[AddLinkheaders] ==>
    classOf[Redirector]).build()

}