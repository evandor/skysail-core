package io.skysail.core.akka

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.event.LoggingReceive
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{ LinkRelation, ResourceAssociationType }
import scala.concurrent.duration.DurationInt

abstract class Resource[T] {

  implicit val askTimeout: Timeout = 1.seconds

  var sendBackTo: ActorRef = null

}
