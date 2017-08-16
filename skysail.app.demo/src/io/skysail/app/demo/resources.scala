package io.skysail.app.demo

import akka.actor.ActorRef
import io.skysail.core.akka.actors._

import scala.concurrent.Future
import scala.reflect.ClassTag


class ContactsController extends ListResourceController[Contact] {
  val appService = new ContactService()

  //override def get(): List[Contact] = appService.getApplications().toList
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
  }
}

//class AppResource extends EntityResource[Application] {
//  val appService = new ApplicationService()
//  override def get(): Application = Application("hi")
//}