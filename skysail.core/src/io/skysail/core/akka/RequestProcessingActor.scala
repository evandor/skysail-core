package io.skysail.core.akka

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.http.scaladsl.server.RequestContext

class RequestProcessingActor[T](nextActor: Props) extends Actor with ActorLogging {
  
  //implicit val system = ActorSystem()
  var returnTo: ActorRef = null
  
  def receive = {
    case (ctx: RequestContext, actor: ResourceActor[_]) => receiveRequestContext(ctx, actor)
    case res: ResponseEvent => receiveResponseEvent(res)
    case any: Any => log error "??? received msg of type " + any.getClass().getName + " with value " + any.toString()
  }
  
  def receiveResponseEvent(res: ResponseEvent) = {
    //log info "finishing Request Processing..."
    //log info "returning msg hiXXX to " + returnTo
    returnTo ! res.httpResponse
  }
  
  private def receiveRequestContext(ctx: RequestContext, resourceActor: ResourceActor[_]) = {
    returnTo = sender
    //log info "starting Request Processing... returnTo set to " + returnTo
    val a = context.actorOf(nextActor, nextActor.actorClass().getSimpleName)
    a ! RequestEvent(ctx,resourceActor)
  }
}

