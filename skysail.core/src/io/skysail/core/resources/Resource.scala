package io.skysail.core.resources

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.event.LoggingReceive
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{ LinkRelation, ResourceAssociationType }
import scala.concurrent.duration.DurationInt
import scala.reflect.runtime.universe._

object Resource {
  implicit class TypeDetector[T: TypeTag](related: Resource[T]) {
    def getType(): Type = typeOf[T]
  }
}

abstract class Resource[T: TypeTag] {

  implicit val askTimeout: Timeout = 1.seconds

  var sendBackTo: ActorRef = null

}
