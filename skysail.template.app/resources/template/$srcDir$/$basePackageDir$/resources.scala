package $basePackageName$;

import io.skysail.core.akka.actors._


class AppsResource extends ListResource[Application] {  
  val appService = new ApplicationService()
  override def get(): List[Application] = appService.getApplications().toList
}

class AppResource extends EntityResource[Application] {
  val appService = new ApplicationService()
  override def get(): Application = Application("hi")
}