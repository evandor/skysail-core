package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel
import scala.reflect.runtime.universe._
import scala.reflect.ClassTag
import io.skysail.core.app.resources.ActorContextAware
import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.util.Timeout
import scala.concurrent.duration.DurationInt
import akka.actor.ActorContext

abstract class AsyncListResource[T: ClassTag] extends Resource[List[T]] with ActorContextAware {

  implicit val askTimeout: Timeout = 1.seconds

  override var actorContext: ActorContext = this.actorContext

  def get(sendBackTo: ActorRef): Unit

}