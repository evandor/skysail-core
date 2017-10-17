package io.skysail.core.akka

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

case class RequestEvent(cmd: ProcessCommand, controllerActor: ActorRef)

trait ResponseEventBase {
  val req: RequestEvent
  val entity: Any
  val httpResponse: HttpResponse
}

case class ResponseEvent[T](req: RequestEvent, val entity: T, val httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase

case class ListResponseEvent[T](req: RequestEvent, val entity: T, val httpResponse: HttpResponse = HttpResponse(200)) extends ResponseEventBase