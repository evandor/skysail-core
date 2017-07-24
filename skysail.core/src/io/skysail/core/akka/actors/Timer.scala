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

class Timer(val nextActorsProps: Props) extends AbstractRequestHandlerActor {
  var start: Long = System.currentTimeMillis()
  override def doRequest(req: RequestEvent) = {
    start = System.currentTimeMillis()
  }
  override def doResponse(res: ResponseEvent) = {
    val stop = System.currentTimeMillis()
    res.httpResponse = res.httpResponse.copy(headers =  res.httpResponse.headers :+ DurationHeader(s"${stop - start}ms"))
    println(":::" + res.httpResponse)
  }

  final class DurationHeader(v: String) extends ModeledCustomHeader[DurationHeader] {
    override def renderInRequests = false
    override def renderInResponses = true
    override val companion = DurationHeader
    override def value: String = v
  }
  
  object DurationHeader extends ModeledCustomHeaderCompanion[DurationHeader] {
    override val name = "X-Duration"
    override def parse(value: String) = Try(new DurationHeader(value))
  }

}