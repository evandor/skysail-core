package $basePackageName$;

import io.skysail.core.akka.actors._
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.resources.AsyncEntityResource
import io.skysail.core.akka.RequestEvent

class AppsResource extends AsyncListResource[Application] {  
  val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
    val r = appService.getApplications().toList
    requestEvent.resourceActor ! ResponseEvent(requestEvent, r)
  }
}

class AppResource extends AsyncEntityResource[Application] {
  val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
    Application("hi")
  }
}