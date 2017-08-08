package io.skysail.core.app.resources

import io.skysail.core.akka._
import io.skysail.core.akka.ResourceActor
import io.skysail.core.akka.actors.Delegator
import io.skysail.core.akka.actors.Timer
import io.skysail.core.akka.actors.Worker
import io.skysail.core.dsl.ActorChainDsl._

class DefaultResource3[String] extends ResourceActor[String] {

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    classOf[Delegator] ==>
    classOf[Worker]).build()

  def get(): String = {
    ???
  }

}
