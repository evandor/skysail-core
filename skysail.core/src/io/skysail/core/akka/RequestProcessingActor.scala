package io.skysail.core.akka

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.RequestContext
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

class RequestProcessingActor[T](nextActor: Props) extends Actor with ActorLogging {
  implicit val system = ActorSystem()
  var returnTo: ActorRef = null
  def receive = {
    case ctx: RequestContext => {
      returnTo = sender
      log info "starting Request Processing... returnTo set to " + returnTo
      val a = context.actorOf(nextActor, nextActor.actorClass().getSimpleName)
      a ! RequestEvent(ctx)
    }
    case res: ResponseEvent => {
      log info "finishing Request Processing..."
      log info "returning msg hiXXX to " + returnTo

      val res = HttpResponse(entity = HttpEntity(
        ContentTypes.`text/html(UTF-8)`,
        "<html><body>Hello world!</body></html>"))

      returnTo ! "res"
    }
    case any: Any => {
      log error "??? received msg of type " + any.getClass().getName + " with value " + any.toString()
    }
  }
}

