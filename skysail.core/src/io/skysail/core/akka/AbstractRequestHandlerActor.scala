package io.skysail.core.akka

import akka.actor._
import akka.actor.Actor._
import java.util.Date
import akka.http.scaladsl.model.HttpRequest

abstract class AbstractRequestHandlerActor extends Actor with ActorLogging {

  var returnTo: ActorRef = null

  def receive = {
    case req: RequestEvent => receivedRequestEvent(req)
    case res: ResponseEvent => receivedResponseEvent(res)
    case msg => log info s"unknown message of type '${msg.getClass.getName}' received"
  }

  def handleRequest(nextActor: ActorRef, req: RequestEvent) = {
    if (nextActor != null) {
      nextActor ! RequestEvent(sender, req.request, req.response)
    } else {
      req.sender ! ResponseEvent(req)
    }
  }

  def handleResponse(nextActor: ActorRef, re: ResponseEvent) = {
    nextActor ! re
  }

  def nextActor(): ActorRef
  def doRequest(req: RequestEvent): Unit = {}
  def doResponse(res: ResponseEvent): Unit = {}

  private def receivedRequestEvent(req: RequestEvent) = {
    log info s"RequestEvent received"
    returnTo = sender
    doRequest(req)
    handleRequest(nextActor(), req)
  }

  private def receivedResponseEvent(re: ResponseEvent) = {
    log info s"ResponseEvent received"
    doResponse(re)
    handleResponse(returnTo, re)
  }
}

class Timer(val nextActor: ActorRef) extends AbstractRequestHandlerActor {
  var start: Long = System.currentTimeMillis()
  override def doRequest(req: RequestEvent) = {
    start = System.currentTimeMillis()
  }
  override def doResponse(res: ResponseEvent) = {
    val stop = System.currentTimeMillis()
    log info s"execution took ${stop - start}ms"
  }
}

class Delegator(val nextActor: ActorRef = null) extends AbstractRequestHandlerActor {}

class Worker() extends AbstractRequestHandlerActor {
  override def receive: Actor.Receive = {
    case req: RequestEvent => {
      log info "uoohhh... workin'"
      sender ! ResponseEvent(req)
    }
  }
  def nextActor() = null
}
