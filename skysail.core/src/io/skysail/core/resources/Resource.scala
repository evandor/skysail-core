package io.skysail.core.resources

import akka.actor.{ActorSelection, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onComplete}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.ResponseEventBase
import io.skysail.core.app.SkysailApplication
import io.skysail.core.model.ApplicationModel
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

object Resource {

  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }

}

abstract class Resource[T /*<: DddElement */ : TypeTag] {

  implicit val askTimeout: Timeout = 1.seconds

  var applicationModel: ApplicationModel = null
  var application: SkysailApplication = null

  def setApplicationModel(model: ApplicationModel) = this.applicationModel = model

  def setApplication(app: SkysailApplication) = this.application = app

  def createRoute(applicationActor: ActorSelection, processCommand: ProcessCommand)(implicit system: ActorSystem): Route = {
    val t = (applicationActor ? processCommand).mapTo[ResponseEventBase]
    onComplete(t) {
      case Success(result) => complete(result.httpResponse)
      case Failure(failure) => /*log error s"Failure>>> ${failure}"; */ complete(StatusCodes.BadRequest, failure)
    }
  }
}
