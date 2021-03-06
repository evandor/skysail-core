//package io.skysail.core.akka.actors
//
//import io.skysail.core.akka.AbstractRequestHandlerActor
//import akka.actor.Props
//import io.skysail.core.akka.RequestEvent
//import io.skysail.core.akka.ResponseEvent
//import akka.http.scaladsl.model.headers.RawHeader
//import akka.http.scaladsl.model.HttpHeader
//import akka.http.scaladsl.model.headers.CustomHeader
//import akka.http.scaladsl.model.headers.ModeledCustomHeader
//import akka.http.scaladsl.model.headers.ModeledCustomHeaderCompanion
//import scala.util.Try
//import akka.http.scaladsl.server.directives.RespondWithDirectives
//import io.skysail.core.model.LinkModel2
//import akka.actor.ActorRef
//import akka.pattern.ask
//import io.skysail.core.app.SkysailApplication
//import io.skysail.core.app.SkysailRootApplication
//import io.skysail.core.server.actors.ApplicationActor
//import io.skysail.core.model.ApplicationModel
//
//class AddLinkheaders(val nextActorsProps: Props, c: Class[_]) extends AbstractRequestHandlerActor {
//
//  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) {
//    val result = scala.collection.mutable.ListBuffer[LinkModel2]()
//    val appActor = SkysailApplication.getApplicationActorSelection(context.system, classOf[SkysailRootApplication].getName)
//    val r = (appActor ? ApplicationActor.GetAppModel()).mapTo[ApplicationModel]
//    
//    r onSuccess {
//      case value => //println("XXX"+value)
//    }
//    
//    //val resourceModel = appModel.resourceModelFor(resource.getClass).get
//
//    //    val listEntities = resource.getEntity().asInstanceOf[List[ScalaEntity[_]]]
////    for (link <- appModel.linksFor(resource.getClass)) {
////      val pathVariables = getPathVariables(link.getUri())
////      if (pathVariables.size == 0) {
////        result += link
////      } else {
////        for (listEntity <- listEntities) {
////          val substituedUrl = pathVariables.foldLeft(link.path)((seed, variable) => seed.replace("{" + variable + "}", listEntity.getId().toString()))
////          result += link.copy(path = substituedUrl)
////        }
////      }
////    }
////    val links = result.toList ::: resource.runtimeLinks()
//    val limitedLinks = "test"//links.map(l => l.asLinkheaderElement()).mkString(",")
//    //val responseHeaders = ScalaHeadersUtils.getHeaders(resource.getResponse())
//    res.httpResponse = res.httpResponse.copy(headers = res.httpResponse.headers :+ LinkHeader(limitedLinks))
//    nextActor ! res
//  }
//  
//  final class LinkHeader(v: String) extends ModeledCustomHeader[LinkHeader] {
//    override def renderInRequests = false
//    override def renderInResponses = true
//    override val companion = LinkHeader
//    override def value: String = v
//  }
//  
//  object LinkHeader extends ModeledCustomHeaderCompanion[LinkHeader] {
//    override val name = "Link"
//    override def parse(value: String) = Try(new LinkHeader(value))
//  }
//
//  private def getPathVariables(path: String) =
//    "\\{([^\\}]*)\\}".r
//      .findAllIn(path)
//      .map { (e => e.toString().replace("{", "").replace("}", "")) }
//      .toList
//}