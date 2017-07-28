package io.skysail.core.akka.actors

import io.skysail.core.restlet.resources._
import io.skysail.core.akka._
import io.skysail.core.akka.dsl.ActorChainDsl._

abstract class ListResource[T] extends ResourceActor[List[T]] {

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    //classOf[Timer] ==>
    classOf[ListRetriever] ==> 
    //classOf[AddLinkheaders] ==>
    classOf[Redirector]).build()

}