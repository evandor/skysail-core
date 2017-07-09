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

class RequestProcessingActor[T](nextActor: Props) extends Actor with ActorLogging {
  implicit val system = ActorSystem()
  var returnTo:ActorRef = null
  def receive = {
    case req: HttpRequest => {
      returnTo = sender
      log info "starting Request Processing... returnTo set to " + returnTo
      val a = context.actorOf(nextActor, nextActor.actorClass().getSimpleName)
      a ! RequestEvent(req)
    }
    case res: ResponseEvent => {
      log info "finishing Request Processing..."
      log info "returning msg hiXXX to " + returnTo
      returnTo ! "hiXXX"
    }
    case any:Any => {
      log error "??? received msg of type " + any.getClass().getName + " with value " + any.toString()
    }
  }
}

