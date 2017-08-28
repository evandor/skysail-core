package io.skysail.core.akka.actors

import akka.actor.Props
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{ AbstractRequestHandlerActor, ResourceController, ResponseEvent }
import io.skysail.core.app.domain.Application

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ jackson, native, DefaultFormats }
import scala.util.Success
import scala.util.Failure
import akka.actor.ActorRef

class ListRetriever[T](val nextActorsProps: Props, c: Class[_]) extends AbstractRequestHandlerActor {

  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) = {
    val r = (res.req.resourceActor ? ResourceController.GetRequest()).mapTo[List[T]]

    val t = Await.result(r, 1.seconds)

    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization

    val m = Marshal(t).to[RequestEntity]
    
    m.onSuccess{
      case value => 
        nextActor ! res.copy(resource = t, httpResponse = res.httpResponse.copy(entity = value))
    }
    
//    m.onComplete {
//      case Success(value) => println(s"Got the callback, meaning = $value")
//      case Failure(e) => e.printStackTrace
//    }

  }

}