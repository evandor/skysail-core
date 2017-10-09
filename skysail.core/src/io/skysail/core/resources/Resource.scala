package io.skysail.core.resources

import akka.util.Timeout
import io.skysail.core.app.SkysailApplication
import io.skysail.core.model.ApplicationModel

import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._

object Resource {
  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }
}

abstract class Resource[T: TypeTag] {

  implicit val askTimeout: Timeout = 1.seconds

  var applicationModel: ApplicationModel = null
  var application: SkysailApplication = null
  def setApplicationModel(model: ApplicationModel) = this.applicationModel = model
  def setApplication(app: SkysailApplication) = this.application = app

}
