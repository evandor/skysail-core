package io.skysail.core.app.resources

import akka.actor.ActorRef
import akka.pattern.ask
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.domain.Application
import io.skysail.core.server.actors.ApplicationsActor.GetAllApplications

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.server.actors.ApplicationsActor

import scala.util.{Failure, Success}
import org.slf4j.LoggerFactory
import io.skysail.core.app.menus.MenuItem
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

class MenusResource extends AsyncListResource[MenuItem] {

  def get(sendBackTo: ActorRef, cmd: ProcessCommand): Unit = {
    
    val appsActor = SkysailApplication.getApplicationsActor(this.actorContext.system)
    
    (appsActor ? ApplicationsActor.GetMenus())
      .mapTo[List[MenuItem]]
      .onComplete {
        case Success(menuItems) => sendBackTo ! menuItems
        case Failure(f) => println(s"failure ${f}")
      }

  }

}