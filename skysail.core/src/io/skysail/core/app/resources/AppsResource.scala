package io.skysail.core.app.resources

import akka.util.Timeout
import io.skysail.core.akka.actors.ListResource
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application

import scala.concurrent.duration.DurationInt

object AppsResource {
  case class GetAllApplications()
}

class AppsResource extends ListResource[Application] {

  override def get(): List[Application] = {
    val appsActor = SkysailApplication.getApplicationsActor(context.system)
    implicit val askTimeout: Timeout = 1.seconds
    log debug "got ApplicationsActor: " + appsActor
    //val l = (appsActor ? GetAllApplications()).mapTo[List[String]]
    //Await.result(l, 1.seconds)
    List(Application("hier"))
  }
  
}