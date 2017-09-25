package io.skysail.core.app.resources

import akka.actor.ActorRef

import scala.reflect.ClassTag
import io.skysail.core.app.domain.User
import io.skysail.core.resources.AsyncEntityResource
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

class CurrentUserController extends AsyncEntityResource[User] {
  
  override def get(sendBackTo: ActorRef, cmd: ProcessCommand) {
    val currentUser = User(0,"anonymous",List())
    //println(currentUser)
    sendBackTo ! List(currentUser) 
  }

//  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//    val currentUser = User(0,"anonymous",List())
//    println(currentUser)
//    // TODO marshalling only works with lists right now !?!?!
//    sender ! List(currentUser) 
//  }

 
}