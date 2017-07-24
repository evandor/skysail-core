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
import io.skysail.core.model.LinkModel2

class AddLinkheaders(val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def doResponse(res: ResponseEvent) = {
    val result = scala.collection.mutable.ListBuffer[LinkModel2]()
//    val resourceModel = appModel.resourceModelFor(resource.getClass).get
//    val listEntities = resource.getEntity().asInstanceOf[List[ScalaEntity[_]]]
//    for (link <- appModel.linksFor(resource.getClass)) {
//      val pathVariables = getPathVariables(link.getUri())
//      if (pathVariables.size == 0) {
//        result += link
//      } else {
//        for (listEntity <- listEntities) {
//          val substituedUrl = pathVariables.foldLeft(link.path)((seed, variable) => seed.replace("{" + variable + "}", listEntity.getId().toString()))
//          result += link.copy(path = substituedUrl)
//        }
//      }
//    }
//    val links = result.toList ::: resource.runtimeLinks()
    val limitedLinks = "test"//links.map(l => l.asLinkheaderElement()).mkString(",")
    //val responseHeaders = ScalaHeadersUtils.getHeaders(resource.getResponse())
    res.httpResponse = res.httpResponse.copy(headers = res.httpResponse.headers :+ LinkHeader(limitedLinks))
  }
  
  final class LinkHeader(v: String) extends ModeledCustomHeader[LinkHeader] {
    override def renderInRequests = false
    override def renderInResponses = true
    override val companion = LinkHeader
    override def value: String = v
  }
  
  object LinkHeader extends ModeledCustomHeaderCompanion[LinkHeader] {
    override val name = "Link"
    override def parse(value: String) = Try(new LinkHeader(value))
  }

  private def getPathVariables(path: String) =
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map { (e => e.toString().replace("{", "").replace("}", "")) }
      .toList
}