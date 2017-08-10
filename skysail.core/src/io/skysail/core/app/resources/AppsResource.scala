package io.skysail.core.app.resources

import akka.util.Timeout
import io.skysail.core.akka.actors.ListResource
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import akka.pattern.ask
import io.skysail.core.server.ApplicationsActor.GetAllApplications

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class AppsResource extends ListResource[Application] {

  implicit val askTimeout: Timeout = 1.seconds

  override def get(): List[Application] = {
    val appsActor = SkysailApplication.getApplicationsActor(context.system)
    val l = (appsActor ? GetAllApplications()).mapTo[List[Application]]
    Await.result(l, 1.seconds)
  }
  
}