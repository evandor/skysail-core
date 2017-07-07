package io.skysail.core.akka

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.HttpRequest

case class RequestEvent(sender: ActorRef, request: HttpRequest, response: HttpResponse = HttpResponse(200))

case class ResponseEvent(req: RequestEvent)