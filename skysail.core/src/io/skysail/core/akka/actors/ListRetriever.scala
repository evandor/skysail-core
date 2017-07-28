package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props
import io.skysail.core.akka.RequestEvent
import io.skysail.core.akka.ResponseEvent
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.HttpHeader
import akka.http.scaladsl.model.headers.CustomHeader
import akka.http.scaladsl.model.headers.ModeledCustomHeader
import akka.http.scaladsl.model.headers.ModeledCustomHeaderCompanion
import scala.util.Try
import akka.http.scaladsl.server.directives.RespondWithDirectives
import akka.http.scaladsl.model.ResponseEntity
import akka.http.scaladsl.model.HttpEntity.Default

class ListRetriever(val nextActorsProps: Props) extends AbstractRequestHandlerActor {
  
  override def doRequest(req: RequestEvent) = {
    val entity = req.resourceActor.get()
  }
  
  override def doResponse(res: ResponseEvent) = {
    val e = res.req.resourceActor.get()
    res.httpResponse = res.httpResponse.copy(entity = e.toString())
  }

}