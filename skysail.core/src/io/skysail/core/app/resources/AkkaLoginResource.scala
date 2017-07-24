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
import io.skysail.core.akka.actors.Redirector
import io.skysail.core.akka.actors.Timer

class AkkaLoginResource[String] extends RedirectResource[String] {
  def get(): String = {
    ???
  }

  def redirectTo(): String = {
    ???
  }
}