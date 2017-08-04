package io.skysail.core.app.resources

import io.skysail.core.akka._
import io.skysail.core.akka.actors.ListResource
import io.skysail.core.app.services.ApplicationService
import io.skysail.core.model.ApplicationModel
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.resources.AppsResource.GetAllApplications
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration.DurationInt
import scala.concurrent.Await

object AppsResource {
  case class GetAllApplications()
}

class AppsResource /*(appModel: ApplicationModel)*/ extends ListResource[String] {
  override def get(): List[String] = {
    val appsActor = SkysailApplication.getApplicationsActor(context.system)
    implicit val askTimeout: Timeout = 1.seconds
    val l = (appsActor ? GetAllApplications()).mapTo[List[String]]
    Await.result(l, 1.seconds)
    //ApplicationService.getApplications
  }
}