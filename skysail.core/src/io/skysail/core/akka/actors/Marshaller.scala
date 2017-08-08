package io.skysail.core.akka.actors

import akka.actor.Props
import io.skysail.core.akka.{AbstractRequestHandlerActor, RequestEvent, ResponseEvent}

class Marshaller(val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def doRequest(res: RequestEvent) = {
    println("hier1: " + res)
  }

  override def doResponse(res: ResponseEvent[_]) = {
    println("hier2 " + res)
    res
  }
}