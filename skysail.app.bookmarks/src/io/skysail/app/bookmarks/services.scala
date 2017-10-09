package io.skysail.app.bookmarks;

import scala.concurrent.Future

class ApplicationService { //(implicit val executionContext: ExecutionContext) {

  //var applications = List[Application]()

  def createApplication(app: Bookmark): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Bookmark]] = ???

  def getApplications(): Seq[Bookmark] = List(Bookmark("skysail","http://www.skysail.io"), Bookmark("pline","http://www.pline.one"))

  //def updateQuestion(id: String, update: QuestionUpdate): Future[Option[Question]] = ...

  //def deleteQuestion(id: String): Future[Unit] = ...

}
