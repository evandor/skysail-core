package io.skysail.core.app.resources

import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.domain.User
import io.skysail.core.resources.AsyncEntityResource

class CurrentUserController extends AsyncEntityResource[User] {
  
  override def get(requestEvent: RequestEvent) {
    val currentUser = User(0,"anonymous",List())
    //println(currentUser)
    requestEvent.resourceActor ! List(currentUser)
  }

//  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//    val currentUser = User(0,"anonymous",List())
//    println(currentUser)
//    // TODO marshalling only works with lists right now !?!?!
//    sender ! List(currentUser) 
//  }

 
}