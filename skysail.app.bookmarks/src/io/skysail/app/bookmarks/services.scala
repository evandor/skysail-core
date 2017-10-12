package io.skysail.app.bookmarks;

import scala.concurrent.Future

class ApplicationService {

  def createApplication(app: Bookmark): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Bookmark]] = ???

  def getApplications(): Seq[Bookmark] = List(
    Bookmark(None,"skysail","http://www.skysail.io"),
    Bookmark(null,"pline","http://www.pline.one")
  )

}
