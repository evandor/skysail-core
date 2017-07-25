package $basePackageName$;

import io.skysail.core.akka.actors._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ApplicationService { //(implicit val executionContext: ExecutionContext) {

  //var applications = List[Application]()

  def createApplication(app: Application): Future[Option[String]] = ???

  def getApplication(id: String): Future[Option[Application]] = ???

  def getApplications(): Seq[Application] = List(Application("hi"), Application("there"))

  //def updateQuestion(id: String, update: QuestionUpdate): Future[Option[Question]] = ...

  //def deleteQuestion(id: String): Future[Unit] = ...

}
