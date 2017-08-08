package io.skysail.core.app.resources

import io.skysail.core.akka._
import io.skysail.core.akka.ResourceActor
import io.skysail.core.akka.actors.Redirector
import io.skysail.core.akka.actors.Timer
import io.skysail.core.dsl.ActorChainDsl._

abstract class RedirectResource[String] extends ResourceActor[String] {

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    classOf[Redirector]).build()
    
  def redirectTo(): String

}