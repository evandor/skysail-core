package io.skysail.core.akka

import akka.actor.Props
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.app.domain.Application

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ jackson, native, DefaultFormats }
import scala.util.Success
import scala.util.Failure
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.http.scaladsl.server.RequestContext
import io.skysail.core.server.actors.ApplicationActor.SkysailContext

class RequestProcessingActor[T](nextActor: Props, c: Class[_]) extends Actor with ActorLogging {

  var returnTo: ActorRef = null

  def receive = {
    case (ctx: SkysailContext, actor: ActorRef) => receiveRequestContext(ctx, actor)
    case res: ResponseEvent[T] => receiveResponseEvent(res)
    case any: Any => log error "??? received msg of type " + any.getClass().getName + " with value " + any.toString()
  }

  private def receiveRequestContext(ctx: SkysailContext, resourceActor: ActorRef) = {
    returnTo = sender
    val actor = context.actorOf(nextActor, nextActor.actorClass().getSimpleName)
    actor ! RequestEvent(ctx, resourceActor)
  }

  private def receiveResponseEvent(response: ResponseEvent[T]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    
    returnTo ! response
  }

}

