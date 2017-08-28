package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._

abstract class PostResource[T] extends ResourceController[T] {

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    //classOf[ListRetriever] ==> 
    classOf[AddLinkheaders] ==>
    classOf[Redirector]).build(null)
    
}