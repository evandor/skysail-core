package io.skysail.core.akka

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.RequestContext

case class RequestEvent(ctx: RequestContext)

case class ResponseEvent(req: RequestEvent, var httpResponse: HttpResponse = HttpResponse(200))