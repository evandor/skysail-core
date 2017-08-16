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
    case res: ResponseEvent[T] => receiveResponseEvent(res)
    case any: Any => log error "??? received msg of type " + any.getClass().getName + " with value " + any.toString()
  }

  private def receiveRequestContext(ctx: RequestContext, resourceActor: ActorRef) = {
    returnTo = sender
    val actor = context.actorOf(nextActor, nextActor.actorClass().getSimpleName)
    actor ! RequestEvent(ctx,resourceActor)
  }

  private def receiveResponseEvent(res: ResponseEvent[T]) = returnTo ! res

}

