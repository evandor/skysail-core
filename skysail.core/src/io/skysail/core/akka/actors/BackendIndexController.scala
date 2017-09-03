package io.skysail.core.akka.actors

import io.skysail.core.akka._
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import scala.reflect.ClassTag
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import akka.pattern.ask
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications

class BackendIndexController extends ListResourceController[String] {

  //val appsActor = SkysailApplication.getApplicationsActor(context.system)

//  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//    //context.
//    sender ! List("root")
//  }

//  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
//    (appsActor ? GetAllApplications())
//      .mapTo[List[Application]]
//      .onSuccess { case r => sender ! r }
//  }
  def get() = ???
}