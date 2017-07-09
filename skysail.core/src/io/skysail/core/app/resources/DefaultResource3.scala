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

class DefaultResource3 extends ResourceDefinition {

  val chain = classOf[RequestProcessingActor[_]] ==>
    classOf[Timer] ==>
    classOf[Delegator] ==>
    classOf[Worker]

  val b = chain.build()

  override val nextActor = b
  var sendBackTo: ActorRef = null
  override def receive = {
    case e => {
      if (sendBackTo != null) {
        println("out")
        sendBackTo ! e
      } else {
        println("in")
        sendBackTo = sender
        nextActor ! e
      }
    }
  }
}