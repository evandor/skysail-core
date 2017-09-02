package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import scala.reflect.ClassTag

abstract class PostResource[T:ClassTag] extends Resource[T] {

   val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    //classOf[ListRetriever] ==> 
    //classOf[AddLinkheaders] ==>
    classOf[Redirector]).build(null)
    
}