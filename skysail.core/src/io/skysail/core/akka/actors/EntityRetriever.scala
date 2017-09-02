package io.skysail.core.akka.actors

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import akka.pattern.ask
import akka.util.Timeout
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import io.skysail.core.akka.{AbstractRequestHandlerActor, Resource, ResponseEvent}
import org.json4s.{DefaultFormats, jackson}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag
import io.skysail.core.akka.ControllerActor

class EntityRetriever[T](val nextActorsProps: Props, entityClass: Class[_]) extends AbstractRequestHandlerActor {

  override def doResponse(nextActor: ActorRef, res: ResponseEvent[_]) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher
    val r = (res.req.resourceActor ? ControllerActor.GetRequest()).mapTo[List[T]]
    println("RRR" + r)
    //r.mapTo(ClassTag])

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