package io.skysail.core.app.resources

import akka.actor.ActorRef
import akka.pattern.ask
import io.skysail.core.akka.actors.ListResource
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.server.ApplicationsActor.GetAllApplications

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag

class AppsResource extends ListResource[Application] {

  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]) {
      val appsActor = SkysailApplication.getApplicationsActor(context.system)
      val l = (appsActor ? GetAllApplications()).mapTo[List[Application]]
      l.onSuccess { case r => sender ! r }
  }
}