package io.skysail.core.akka

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date
import akka.http.scaladsl.model.HttpRequest

abstract class AbstractRequestHandlerActor extends Actor with ActorLogging {

  var returnTo: ActorRef = null
  
  def handleRequest(nextActor: ActorRef, re: RequestEvent2) = {
    if (nextActor != null) {
      nextActor ! RequestEvent2(sender, re.response)
    } else {
      re.sender ! ResponseEvent()
    }
  }
  
  def handleResponse(nextActor: ActorRef, re: ResponseEvent) = {
    nextActor ! re
  }
}

object Timer {
  def props(nextActor: ActorRef) = Props(new Timer(nextActor))
}

class Timer(nextActor: ActorRef = null) extends AbstractRequestHandlerActor {
  
  var start: Long = System.currentTimeMillis()
  def receive = {
    case re: RequestEvent2 => {
      start = System.currentTimeMillis()
      log info "started..."
      returnTo = sender
      super.handleRequest(nextActor, re)
    }
    case re: ResponseEvent => {
      val stopped = System.currentTimeMillis() - start
      log info s"timer stopped after ${stopped}ms!"
      super.handleResponse(returnTo, re)
    }
  }
}

object Delegator {
  def props(nextActor: ActorRef) = Props(new Delegator(nextActor))
}

class Delegator(nextActor: ActorRef = null) extends AbstractRequestHandlerActor {
  def receive = {
    case re: RequestEvent2 => {
      log info "delegator started..."
      returnTo = sender
      super.handleRequest(nextActor, re)
    }
    case re: ResponseEvent => {
      log info s"stopped"
      super.handleResponse(returnTo, re)
    }
  }
}

class Worker() extends AbstractRequestHandlerActor {
  def receive: Actor.Receive = {
    case _ => {
      log info "uoohhh... workin'"
      sender ! ResponseEvent()
    }
  }
}
