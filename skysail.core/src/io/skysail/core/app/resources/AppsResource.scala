package io.skysail.core.app.resources

import akka.actor.ActorRef
import akka.pattern.ask
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand
import io.skysail.core.server.actors.ApplicationsActor

import scala.util.{Failure, Success}
import org.slf4j.LoggerFactory

class AppsResource extends AsyncListResource[Application] {

  private val log = LoggerFactory.getLogger(this.getClass())

  def get(sendBackTo: ActorRef, cmd: ProcessCommand): Unit = {
    
    val appsActor = SkysailApplication.getApplicationsActor(this.actorContext.system)
    
    (appsActor ? ApplicationsActor.GetAllApplications())
      .mapTo[List[Application]]
      .onComplete {
        case Success(s) => sendBackTo ! s
        case Failure(f) => log error s"failure ${f}"
      }

  }

}