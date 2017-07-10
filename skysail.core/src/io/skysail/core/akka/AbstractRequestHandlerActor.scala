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

  def nextActorsProps(): Props
  def doRequest(req: RequestEvent): Unit = {}
  def doResponse(res: ResponseEvent): Unit = {}

  private def receivedRequestEvent(req: RequestEvent) = {
    log info s"RequestEvent received"
    log info s"setting returnTo to " + sender
    returnTo = sender
    doRequest(req)
    if (nextActorsProps != null) {
      val a = context.actorOf(nextActorsProps(), nextActorsProps().actorClass().getSimpleName)
      log info s"proceeding with " + a
      a ! RequestEvent(req.ctx, req.response)
    } else {
      log info s"returning to " + returnTo
      returnTo ! ResponseEvent(req)
    }
  }

  private def receivedResponseEvent(res: ResponseEvent) = {
    log info s"ResponseEvent received"
    doResponse(res)
    log info s"returning to $returnTo with $res"
    returnTo ! res
  }
  
  
}

class Timer(val nextActorsProps: Props) extends AbstractRequestHandlerActor {
  var start: Long = System.currentTimeMillis()
  override def doRequest(req: RequestEvent) = {
    start = System.currentTimeMillis()
  }
  override def doResponse(res: ResponseEvent) = {
    val stop = System.currentTimeMillis()
    log info s"execution took ${stop - start}ms"
  }
}

class Delegator(val nextActorsProps: Props = null) extends AbstractRequestHandlerActor

class Worker(val nextActorsProps: Props = null) extends AbstractRequestHandlerActor 
