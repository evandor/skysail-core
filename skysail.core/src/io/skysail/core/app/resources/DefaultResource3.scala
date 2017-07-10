package io.skysail.core.app.resources

import io.skysail.core.restlet.resources._
import io.skysail.core.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication
import io.skysail.core.restlet.menu.MenuItem
import io.skysail.core.restlet.resources.ListServerResource
import io.skysail.core.akka.ResourceDefinition
import io.skysail.core.akka.RequestProcessingActor
import akka.actor.Props
import io.skysail.core.akka._
import io.skysail.core.akka.ActorChainDsl._
import akka.actor.ActorRef
import io.skysail.core.akka.actors.Redirector

class AkkaRedirectResource extends ResourceDefinition {

  override val chainRoot = (
    classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    classOf[Redirector]).build()

  /*val actorChainRoot = chain.build()

  //override val nextActor = actorChainRoot
  var sendBackTo: ActorRef = null
  override def receive = {
    case e => {
      if (sendBackTo != null) {
        println("out")
        sendBackTo ! e
        log info "stopping actor: " + actorChainRoot
        context.stop(actorChainRoot)
      } else {
        println("in")
        sendBackTo = sender
        actorChainRoot ! e
      }
    }
  }*/
}