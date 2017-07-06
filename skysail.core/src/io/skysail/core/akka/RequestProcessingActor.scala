package io.skysail.core.akka

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date
import akka.http.scaladsl.model.HttpRequest

object RequestProcessingActor {
  def then(nextActor: ActorRef) = Props(new RequestProcessingActor(nextActor))
  def then(cls: Class[_]) = {
    implicit val system = ActorSystem()
    val p = Props.apply(cls,null)
    val a = system.actorOf(p)
    Props(new RequestProcessingActor(a))
  }
}

class RequestProcessingActor[T](nextActor: ActorRef) extends Actor with ActorLogging {
  implicit val system = ActorSystem()
  def receive = {
    case r: HttpRequest => {
      log info "starting Request Processing..."
      nextActor ! RequestEvent2(sender)
    }
    case _ => {
      log error "???"
    }
  }
}

