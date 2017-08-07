package io.skysail.core.akka.actors

import io.skysail.core.akka.AbstractRequestHandlerActor
import akka.actor.Props
import io.skysail.core.akka.ResponseEvent
import io.skysail.core.akka.ResourceActor
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.duration.DurationInt

class ListRetriever(val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  override def doResponse(res: ResponseEvent) = {
    implicit val askTimeout: Timeout = 1.seconds
    implicit val ec = context.system.dispatcher
    val r = (res.req.resourceActor ? ResourceActor.GetRequest()).mapTo[List[_]]
//    r.onComplete {
//      case Success(value) => println(s"Got the callback, meaning = $value")
//      case Failure(e) => e.printStackTrace
//    }
//    res.httpResponse = res.httpResponse.copy(entity = "e.toString()")
    val t = Await.result(r, 1.seconds)
    //println("YYY: " + t)
    val result = res.copy(resource = t, httpResponse = res.httpResponse.copy(entity = "dort: " + t.toString))
    //res.httpResponse = res.httpResponse.copy(entity = "hier: " + t.toString)
    //println("YYY: " + result)
    result
  }

}