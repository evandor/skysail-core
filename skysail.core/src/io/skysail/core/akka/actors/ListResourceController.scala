package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel
import scala.reflect.runtime.universe._
import scala.reflect.ClassTag

abstract class ListResourceController[T:ClassTag] extends ResourceController[List[T]] {

  val ctag = implicitly[reflect.ClassTag[T]]
  val xxx = ctag.runtimeClass.asInstanceOf[Class[T]]
  
  override val chainRoot = (
    classOf[RequestProcessingActor[T]] ==>
    classOf[Timer] ==>
    classOf[ListRetriever[T]] ==>
    classOf[AddLinkheaders]).build(xxx)

}