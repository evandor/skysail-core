package io.skysail.app.bookmarks;

import io.skysail.core.akka.actors._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ApplicationService { //(implicit val executionContext: ExecutionContext) {

  //var applications = List[Application]()

  def createApplication(app: Bookmark): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Bookmark]] = ???

  def getApplications(): Seq[Bookmark] = List(Bookmark("hi",""), Bookmark("there",""))

  //def updateQuestion(id: String, update: QuestionUpdate): Future[Option[Question]] = ...

  //def deleteQuestion(id: String): Future[Unit] = ...

}
