package io.skysail.core.app.resources

import io.skysail.core.restlet.resources._
import io.skysail.core.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication
import io.skysail.core.restlet.menu.MenuItem
import io.skysail.core.akka.ResourceActor
import io.skysail.core.akka.RequestProcessingActor
import akka.actor.Props
import io.skysail.core.akka._
import io.skysail.core.akka.dsl.ActorChainDsl._
import akka.actor.ActorRef
import io.skysail.core.akka.actors.Timer
import io.skysail.core.akka.actors.Delegator
import io.skysail.core.akka.actors.Worker

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