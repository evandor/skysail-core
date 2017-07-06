package io.skysail.core.akka

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date
import akka.http.scaladsl.model.HttpRequest

//object AbstractRequestHandlerActor {
//  def props(nextActor: ActorRef) = Props(new RequestProcessingActor(nextActor))
//}

abstract class AbstractRequestHandlerActor extends Actor with ActorLogging {
  def handleRequest(nextActor: ActorRef, re: RequestEvent2) = {
    if (nextActor != null) {
      nextActor ! RequestEvent2(sender, re.response)
    } else {
      re.sender ! ResponseEvent()
    }
  }
}

object Timer {
  def props(nextActor: ActorRef) = Props(new Timer(nextActor))
}

class Timer(nextActor: ActorRef = null) extends AbstractRequestHandlerActor {
  var returnTo:ActorRef = null
  def receive: Actor.Receive = {
    case re: RequestEvent2 => {
      log info "starting @" + System.currentTimeMillis()
      returnTo = sender
      super.handleRequest(nextActor, re)
    }
    case re: ResponseEvent => {
      log info "stopping @" + System.currentTimeMillis()
      sender ! re
    }
    
    case _ => log info "error"
  }
}

class Worker() extends AbstractRequestHandlerActor {
  def receive: Actor.Receive = {
    case _ => sender ! ResponseEvent()
  }
}
