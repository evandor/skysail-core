package io.skysail.app.wyt

import io.skysail.core.akka.actors._
import scala.concurrent.Future

class PactsService {

  def createApplication(app: Pact): Future[Option[String]] = ???
  def getPact(id: String): Future[Option[Pact]] = ???
  def getPacts(): Seq[Pact] = List(Pact("hi"), Pact("there"))

}
