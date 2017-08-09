package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{ContentType, ContentTypes, HttpEntity, MessageEntity}
import io.skysail.core.akka.ResponseEvent
import io.skysail.core.akka.ResourceActor
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.duration.DurationInt
import akka.http.scaladsl.model.headers.`Content-Type`
import akka.http.scaladsl.model.ContentTypes._
import spray.json._
import DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson._
import spray.json.DefaultJsonProtocol

class ListRetriever[T](val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def doResponse(res: ResponseEvent[_]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher
    val r = (res.req.resourceActor ? ResourceActor.GetRequest()).mapTo[List[T]]
//    r.onComplete {
//      case Success(value) => println(s"Got the callback, meaning = $value")
//      case Failure(e) => e.printStackTrace
//    }
//    res.httpResponse = res.httpResponse.copy(entity = "e.toString()")
    val t = Await.result(r, 1.seconds)
    //println("YYY: " + t)

    //val e = Await.result(Marshal("""{"bar1": "foo2"}""").to[MessageEntity], 1.seconds)

    //implicit val itemFormat = jsonFormat2(List)
    //val jsonAst = t.asInstanceOf[List[String]].toJson.toString()
    //new Application("hier")
    val jsonAst = List("1","2","3").toJson.toString()

    val httpEntity = HttpEntity(ContentTypes.`application/json`, jsonAst)
    val result = res.copy(resource = t, httpResponse = res.httpResponse.copy(entity = httpEntity))
    result
  }

}