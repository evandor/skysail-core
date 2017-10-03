package io.skysail.app.demo

import akka.actor.ActorRef
import akka.http.scaladsl.model.{ContentTypes, ResponseEntity}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import akka.util.ByteString
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import io.skysail.core.app.SkysailApplication
import io.skysail.core.resources.AsyncListResource
import io.skysail.core.server.actors.ApplicationActor
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.{ClientProtocolException, ResponseHandler}
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.json4s.{DefaultFormats, jackson}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag
import akka.pattern.ask
import io.skysail.core.akka.RequestEvent

import scala.util.{Failure, Success}

class EsResource extends AsyncListResource[DemoRoot] {

  def get(requestEvent: RequestEvent): Unit = {
    requestEvent.resourceActor ! List(
      DemoRoot("indices", "/demo/v1/indices", "ElasticSearch Indices"),
      DemoRoot("config", "/demo/v1/configs", "System Configuration"))
  }
}

class IndicesResource extends AsyncListResource[EsIndex] {

  private val httpclient = HttpClients.createDefault

  val appService = new ContactService()

  //  @AuthorizeByRole("esadmin")

  def get(requestEvent: RequestEvent): Unit = {
    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    implicit val system = actorContext.system
    implicit val materializer = ActorMaterializer()

    val res = get("http://localhost:9200/_cat/indices?format=json")
    val v = akka.http.scaladsl.model.HttpEntity.Strict(ContentTypes.`application/json`, ByteString(res)).asInstanceOf[ResponseEntity]
    val x = Unmarshal(v)
    val u = x.to[List[EsIndex]]

    u onSuccess {
      case value => {
        requestEvent.resourceActor ! value
      }
    }

    u onFailure {
      case failure => println("FAILURE: " + failure)
    }
  }

  def get(path: String) /*(implicit system: ActorSystem = ActorSystem())*/ = {
    //    implicit val materializer = ActorMaterializer()
    //
    //    val source = Source.single(HttpRequest(uri = Uri(path = Path("/_cat/indices?format=json"))))
    //    val flow = Http().outgoingConnectionHttps("localhost", 9200).mapAsync(1) { r =>
    //      Unmarshal(r.entity).to[EsIndex]
    //    }
    //
    //    source.via(flow).runWith(Sink.head)

    val httpget = new HttpGet(path)
    val responseHandler = new ResponseHandler[String]() {
      override def handleResponse(response: HttpResponse): String = {
        val status = response.getStatusLine.getStatusCode
        if (status >= 200 && status < 300) {
          val entity = response.getEntity
          if (entity != null) EntityUtils.toString(entity)
          else null
        } else throw new ClientProtocolException("Unexpected response status: " + status)
      }
    }
    httpclient.execute(httpget, responseHandler)
  }

}

class MappingResource extends AsyncListResource[Mapping] {

  private val httpclient = HttpClients.createDefault

  //  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
  //    implicit val formats = DefaultFormats
  //    implicit val serialization = jackson.Serialization
  //    implicit val materializer = ActorMaterializer()
  //
  //    val res = get("http://localhost:9200/logstash-2016.10.25/_mappings")
  //    val v = akka.http.scaladsl.model.HttpEntity.Strict(ContentTypes.`application/json`, ByteString(res)).asInstanceOf[ResponseEntity]
  //    val x = Unmarshal(v)
  //    val u = x.to[List[EsIndex]]
  //
  //    u onSuccess {
  //      case value => {
  //        sender ! value
  //      }
  //    }
  //
  //    u onFailure {
  //      case failure => println("FAILURE: " + failure)
  //    }
  //  }

  //  def get() = ???
  //
  //  def get(path: String) = {
  //    val httpget = new HttpGet(path)
  //    val responseHandler = new ResponseHandler[String]() {
  //      override def handleResponse(response: HttpResponse): String = {
  //        val status = response.getStatusLine.getStatusCode
  //        if (status >= 200 && status < 300) {
  //          val entity = response.getEntity
  //          if (entity != null) EntityUtils.toString(entity)
  //          else null
  //        } else throw new ClientProtocolException("Unexpected response status: " + status)
  //      }
  //    }
  //    httpclient.execute(httpget, responseHandler)
  //  }

  def get[T](requestEvent: RequestEvent)(implicit c: ClassTag[T]): Unit = {
    ???
  }

  def get(requestEvent: RequestEvent): Unit = {
    ???
  }
}

class ConfigsResource extends AsyncListResource[ConfigDetails] {

  def get(requestEvent: RequestEvent): Unit = {
    val appActor = SkysailApplication.getApplicationActorSelection(actorContext.system, classOf[DemoApplication].getName)
    val r = (appActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
    r onComplete {
      case Success(app) => requestEvent.resourceActor ! app.getConfigs()
      case Failure(failure) => println (s"FAILURE: $failure")
    }
  }

}