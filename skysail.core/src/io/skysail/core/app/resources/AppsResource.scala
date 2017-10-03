package io.skysail.core.app.resources

import akka.pattern.ask
import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.server.actors.ApplicationsActor
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class AppsResource extends AsyncListResource[Application] {

  private val log = LoggerFactory.getLogger(this.getClass())

  def get(requestEvent: RequestEvent): Unit = {
    
    val appsActor = SkysailApplication.getApplicationsActor(this.actorContext.system)
    
    (appsActor ? ApplicationsActor.GetAllApplications())
      .mapTo[List[Application]]
      .onComplete {
        case Success(s) => requestEvent.resourceActor ! s
        case Failure(f) => log error s"failure ${f}"
      }

  }

}