package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import scala.reflect.ClassTag
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorRef

abstract class AsyncEntityResource[T: ClassTag] extends Resource[T] with ActorContextAware {
  
  def get(sendBackTo: ActorRef): Unit

//   val ctag = implicitly[reflect.ClassTag[T]]
//  val xxx = ctag.runtimeClass.asInstanceOf[Class[T]]
//   val chainRoot = (
//    classOf[RequestProcessingActor[T]] ==>
//      classOf[Timer] ==>
//      classOf[EntityRetriever[T]] 
//      //classOf[AddLinkheaders]
//      ).build(xxx)


}