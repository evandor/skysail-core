package io.skysail.core.akka

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import akka.event.LoggingReceive
import akka.http.scaladsl.model.RequestEntity
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{ LinkRelation, ResourceAssociationType }

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag
import io.skysail.core.server.actors.ApplicationActor.SkysailContext
import org.json4s.{ DefaultFormats, jackson }
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import io.skysail.core.resources.AsyncListResource

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ jackson, native, DefaultFormats }
import scala.util.Success
import scala.util.Failure
import akka.http.scaladsl.server.RequestContext
import akka.http.scaladsl.model.Uri
import io.skysail.core.model.ApplicationModel
import org.osgi.framework.BundleContext
import io.skysail.core.app.resources.ActorContextAware
import io.skysail.core.resources.AsyncStaticResource
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ResponseEntity
import io.skysail.core.resources._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpMethods
import io.skysail.core.app.resources.PostSupport

object ControllerActor {
  case class GetRequest()
  case class PostRequest()
  case class PutRequest()
  case class DeleteRequest()
  case class MyResponseEntity(val entity: ResponseEntity)
}

class ControllerActor[T]( /*resource: Resource[_]*/ ) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  // log debug s"new ControllerActor created -> Resource('${resource}')"

  val originalSender = sender
  var sendBackTo: ActorRef = null

  import context._

  def receive = in

  def in: Receive = LoggingReceive {
    case SkysailContext(ctx: RequestContext, model: ApplicationModel, resource: AsyncResource[T], _: Option[BundleContext], _: Uri.Path) => {
      sendBackTo = sender
      resource.setActorContext(context)
      resource.setApplicationModel(model)
      ctx.request.method match {
        case HttpMethods.POST => resource.asInstanceOf[PostSupport].post(self)
        case e: Any => resource.get(self)
      }
      become(out)
    }
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out: Receive = LoggingReceive {
    case msg: List[T] => {
      //implicit val ec = context.system.dispatcher
      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization
      val m = Marshal(msg).to[RequestEntity]
      m.onSuccess {
        case value =>
          val reqEvent = RequestEvent(null, null)
          val resEvent = ResponseEvent(reqEvent, null)
          sendBackTo ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = value))
      }
    }
    case msg: ControllerActor.MyResponseEntity => {
      val reqEvent = RequestEvent(null, null)
      val resEvent = ResponseEvent(reqEvent, null)
      sendBackTo ! resEvent.copy(httpResponse = resEvent.httpResponse.copy(entity = msg.entity))
    }
    case msg: T => { /* and EntityDescription */
      println("msg: T " + msg)
      val reqEvent = RequestEvent(null, null)
      val resEvent = ResponseEvent(reqEvent, null)
      //implicit val ec = context.system.dispatcher
      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization
      //val t = Marshal(msg)
      val m = Marshal(List(msg)).to[RequestEntity]
      
      
      //sendBackTo ! resEvent.copy(httpResponse = resEvent.httpResponse.copy(entity = HttpEntity(msg.toString)))
       m onComplete {
        case Success(value) =>
          val reqEvent = RequestEvent(null, null)
          val resEvent = ResponseEvent(reqEvent, null)
          sendBackTo ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = value))
        case Failure(failure) => println (s"Failure: ${failure}")
      }

    }
    case msg: Any => log info s"OUT: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

}
