package io.skysail.core.akka

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse

case class RequestEvent2(sender: ActorRef, response: HttpResponse = HttpResponse(200))

case class ResponseEvent()