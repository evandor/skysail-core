package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props
import io.skysail.core.akka.ResponseEvent

class Worker(val nextActorsProps: Props = null) extends AbstractRequestHandlerActor {
  override def doResponse(res: ResponseEvent) = {
    res.httpResponse = res.httpResponse.copy(entity = "hi2!!!")
  }
}