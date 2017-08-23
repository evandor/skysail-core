package io.skysail.app.demo

import akka.actor.ActorRef
import akka.http.scaladsl.unmarshalling.Unmarshal
import io.skysail.core.akka.actors._
import org.apache.http.{HttpEntity, HttpResponse}
import org.apache.http.client.{ClientProtocolException, ResponseHandler}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils

import scala.concurrent.Future
import scala.reflect.ClassTag



class ContactsController extends ListResourceController[Contact] {
  val appService = new ContactService()

  //override def get(): List[Contact] = appService.getApplications().toList
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
    sender ! List(Contact("Mira"),Contact("carsten"))
  }
}

class IndicesController extends ListResourceController[EsIndex] {

  private val httpclient = HttpClients.createDefault

  val appService = new ContactService()

  //override def get(): List[Contact] = appService.getApplications().toList
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {
    val res = get("http://es01-ig1-dmz:11001/_cat/indices?format=json")

//    val u = Unmarshal(res).to[EsIndex]
//
//    u onSuccess {
//      case value => {
//        println("UUU" + value)
//        sender ! value
//      }
//    }

    sender ! List(res)
  }

  private def get(path: String) = {
    val httpget = new HttpGet(path)
    val responseHandler = new ResponseHandler[String]() {
      override def handleResponse(response: HttpResponse): String = {
        val status = response.getStatusLine.getStatusCode
        if (status >= 200 && status < 300) {
          val entity = response.getEntity
          if (entity != null) EntityUtils.toString(entity)
          else null
        }
        else throw new ClientProtocolException("Unexpected response status: " + status)
      }
    }
    httpclient.execute(httpget, responseHandler)
  }
}

//class AppResource extends EntityResource[Application] {
//  val appService = new ApplicationService()
//  override def get(): Application = Application("hi")
//}