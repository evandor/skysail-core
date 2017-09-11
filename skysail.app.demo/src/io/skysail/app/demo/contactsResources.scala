package io.skysail.app.demo

import akka.actor.ActorRef
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.resources.AsyncPostResource

class ContactsResource extends AsyncListResource[Contact] {
  val appService = new ContactService()
  def get(sender: ActorRef): Unit = {
    sender ! List(Contact("a@b.com", "Mira", "Gräf"), Contact("b@c.com", "carsten", "Gräf"))
  }
}

class PostContactResource extends AsyncPostResource[Contact] {
  
  def get(sender: ActorRef): Unit = {
    val entityModel = applicationModel.entityModelFor(classOf[Contact])
    if (entityModel.isDefined) {
      println("EM: " + entityModel.get.fields)
      println("EM: " + entityModel.get.description())
      sender ! entityModel.get.description()
    } else {
      sender ! Contact("a@b.com", "Mira", "Gräf")//describe(classOf[Contact])
    }  
  }

  def post(sendBackTo: ActorRef): Unit = {
    ???
  }
}