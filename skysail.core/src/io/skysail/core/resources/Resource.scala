package io.skysail.core.resources

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.event.LoggingReceive
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{ LinkRelation, ResourceAssociationType }
import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._
import io.skysail.core.model.ApplicationModel
import io.skysail.core.app.SkysailApplication

object Resource {
  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }
}

abstract class Resource[T: TypeTag] {

  implicit val askTimeout: Timeout = 1.seconds

  //var sendBackTo: ActorRef = null
  
  var applicationModel: ApplicationModel = null
  
  var application: SkysailApplication = null
  
  def setApplicationModel(model: ApplicationModel) = this.applicationModel = model
  
  def setApplication(app: SkysailApplication) = this.application = app

}
