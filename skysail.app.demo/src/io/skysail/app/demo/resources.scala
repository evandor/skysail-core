package io.skysail.app.demo

import akka.actor.ActorRef
import akka.http.scaladsl.unmarshalling.Unmarshal
import io.skysail.core.akka.actors._
import org.apache.http.{ HttpEntity, HttpResponse }
import org.apache.http.client.{ ClientProtocolException, ResponseHandler }
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{ CloseableHttpClient, HttpClients }
import org.apache.http.util.EntityUtils

import scala.reflect.ClassTag
import org.json4s.{ DefaultFormats, jackson, native }
import akka.http.scaladsl.model.ContentTypes
import akka.util.ByteString
import akka.http.scaladsl.model.ResponseEntity
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._

import scala.concurrent.ExecutionContext.Implicits.global
import io.skysail.core.resources.Resource
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.ApplicationActor
import akka.pattern.ask
import scala.util.Success
import scala.util.Failure
import io.skysail.core.resources.AsyncListResource

class EsResource extends AsyncListResource[DemoRoot] {

  def get(sender: ActorRef): Unit = {
    sender ! List(
      DemoRoot("indices", "/demo/v1/indices", "ElasticSearch Indices"),
      DemoRoot("config", "/demo/v1/configs", "System Configuration"))
  }
}

class IndicesResource extends AsyncListResource[EsIndex] {

  private val httpclient = HttpClients.createDefault

  val appService = new ContactService()

  //  @AuthorizeByRole("esadmin")

  def get(sender: ActorRef): Unit = {
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
        sender ! value
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

  def get() = ???

  def get(path: String) = {
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

  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
    ???
  }

  def get(sendBackTo: ActorRef): Unit = {
    ???
  }
}

class ConfigsResource extends AsyncListResource[ConfigDetails] {
  //  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
  //    val appActor = SkysailApplication.getApplicationActorSelection(context.system, classOf[DemoApplication].getName)
  //    val r = (appActor ? ApplicationActor.GetApplication()).mapTo[DemoApplication]
  //    r onComplete {
  //      case Success(app) => sender ! app.getConfigs()
  //      case Failure(failure) => log error s"$failure"
  //    }
  //  }
  //}
  def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
    ???
  }

  def get() = ???

  def get(sendBackTo: ActorRef): Unit = {
    ???
  }

}