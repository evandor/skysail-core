package io.skysail.core.akka

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.RequestContext
import io.skysail.core.server.ApplicationActor.SkysailContext

case class RequestEvent(ctx: SkysailContext,resourceActor: ActorRef)

case class ResponseEvent[T](req: RequestEvent, val resource: T, var httpResponse: HttpResponse = HttpResponse(200))