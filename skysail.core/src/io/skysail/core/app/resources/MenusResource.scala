package io.skysail.core.app.resources

import akka.pattern.ask
import io.skysail.core.akka.RequestEvent
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.menus.MenuItem
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.server.actors.ApplicationsActor

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class MenusResource extends AsyncListResource[MenuItem] {

  def get(requestEvent: RequestEvent): Unit = {
    
    val appsActor = SkysailApplication.getApplicationsActor(this.actorContext.system)
    
    (appsActor ? ApplicationsActor.GetMenus())
      .mapTo[List[MenuItem]]
      .onComplete {
        case Success(menuItems) => requestEvent.controllerActor ! menuItems
        case Failure(f) => println(s"failure ${f}")
      }

  }

}