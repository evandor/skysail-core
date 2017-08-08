package io.skysail.core.akka

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.http.scaladsl.server.RequestContext

class RequestProcessingActor[T](nextActor: Props) extends Actor with ActorLogging {
  
  var returnTo: ActorRef = null
  
  def receive = {
    case (ctx: RequestContext, actor: ActorRef) => receiveRequestContext(ctx, actor)
    case res: ResponseEvent[_] => receiveResponseEvent(res)
    case any: Any => log error "??? received msg of type " + any.getClass().getName + " with value " + any.toString()
  }
  
  private def receiveRequestContext(ctx: RequestContext, resourceActor: ActorRef) = {
    returnTo = sender
    val a = context.actorOf(nextActor, nextActor.actorClass().getSimpleName)
    a ! RequestEvent(ctx,resourceActor)
  }

  private def receiveResponseEvent(res: ResponseEvent[_]) = {
    returnTo ! res
  }


}

