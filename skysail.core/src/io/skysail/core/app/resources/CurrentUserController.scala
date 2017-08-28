package io.skysail.core.app.resources

import akka.actor.ActorRef
import scala.reflect.ClassTag
import io.skysail.core.app.domain.User
import io.skysail.core.akka.actors.EntityResourceController

class CurrentUserController extends EntityResourceController[User] {

  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
    val currentUser = User(0,"anonymous",List())
    println(currentUser)
    // TODO marshalling only works with lists right now !?!?!
    sender ! List(currentUser) 
  }

}