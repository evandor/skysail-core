package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import scala.reflect.ClassTag

abstract class EntityResourceController[T: ClassTag] extends Resource[T] {

   val ctag = implicitly[reflect.ClassTag[T]]
  val xxx = ctag.runtimeClass.asInstanceOf[Class[T]]
   val chainRoot = (
    classOf[RequestProcessingActor[T]] ==>
      classOf[Timer] ==>
      classOf[EntityRetriever[T]] 
      //classOf[AddLinkheaders]
      ).build(xxx)


}