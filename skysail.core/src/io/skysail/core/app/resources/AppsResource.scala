package io.skysail.core.app.resources

import akka.actor.ActorRef
import akka.pattern.ask
import io.skysail.core.akka.actors.ListResourceController
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import io.skysail.core.akka.actors.AsyncListResource
import io.skysail.core.server.actors.ApplicationsActor
import scala.util.{ Success, Failure }
import org.slf4j.LoggerFactory

class AppsResource extends AsyncListResource[Application] {

  private val log = LoggerFactory.getLogger(this.getClass())

  def get(sendBackTo: ActorRef): Unit = {
    
    val appsActor = SkysailApplication.getApplicationsActor(this.actorContext.system)
    
    log debug s"about to send message GetAllApplications to ${appsActor}"

    (appsActor ? ApplicationsActor.GetAllApplications())
      .mapTo[List[Application]]
      .onComplete {
        case Success(s) => log info s"sending ${sendBackTo} ! ${s}"; sendBackTo ! s
        case Failure(f) => log error s"failure ${f}"
      }

  }

}