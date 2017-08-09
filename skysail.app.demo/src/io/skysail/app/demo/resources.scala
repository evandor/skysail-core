package io.skysail.app.demo

import io.skysail.core.akka.actors._


class ContactsResource extends ListResource[Contact] {  
  val appService = new ContactService()
  override def get(): List[Contact] = appService.getApplications().toList
}

//class AppResource extends EntityResource[Application] {
//  val appService = new ApplicationService()
//  override def get(): Application = Application("hi")
//}