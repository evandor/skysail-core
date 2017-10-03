package io.skysail.core.akka

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand

case class RequestEvent(cmd: ProcessCommand, resourceActor: ActorRef)

case class ResponseEvent[T](req: RequestEvent, val resource: T, var httpResponse: HttpResponse = HttpResponse(200))