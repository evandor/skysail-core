package io.skysail.core.akka.actors

import akka.actor.Props
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.core.akka.{ AbstractRequestHandlerActor, ResourceActor, ResponseEvent }
import io.skysail.core.app.domain.Application
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ jackson, native, DefaultFormats }
import scala.util.Success
import scala.util.Failure

class ListRetriever[T](val nextActorsProps: Props) extends AbstractRequestHandlerActor {

  case class Foo(bar: String) {
    require(bar == "bar", "bar must be 'bar'!")
  }

  private val foo = Foo("bar")

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
    println("YYY: " + t)

    //val e = Await.result(Marshal("""{"bar1": "foo2"}""").to[MessageEntity], 1.seconds)

    //implicit val itemFormat = jsonFormat2(List)
    //val jsonAst = t.asInstanceOf[List[String]].toJson.toString()
    val app1 = new Application("hier1")
    val app2 = new Application("dort2")
    //val jsonAst = List("1","2","3").toJson.toString()
    implicit val appFormat = jsonFormat1(Application)
    //val jsonAst = List(app1,app2).toJson.toString()
    val jsonAst = t.asInstanceOf[List[Application]].toJson.toString()

    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization

    val m = Marshal(t).to[RequestEntity]
    m.onComplete {
      case Success(value) => println(s"Got the callback, meaning = $value")
      case Failure(e) => e.printStackTrace
    }

    val httpEntity = HttpEntity(ContentTypes.`application/json`, jsonAst)
    val result = res.copy(resource = t, httpResponse = res.httpResponse.copy(entity = httpEntity))
    result
  }

}