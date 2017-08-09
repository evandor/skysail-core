package io.skysail.app.demo

import io.skysail.core.akka.actors._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ContactService { //(implicit val executionContext: ExecutionContext) {

  //var applications = List[Application]()

  def createApplication(app: Contact): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Contact]] = ???

  def getApplications(): Seq[Contact] = List(Contact("mira"), Contact("carsten"))

  //def updateQuestion(id: String, update: QuestionUpdate): Future[Option[Question]] = ...

  //def deleteQuestion(id: String): Future[Unit] = ...

}
