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
    case SkysailContext(_: RequestContext, ApplicationModel(_, _, _, _), resource: AsyncListResource[T], _: Option[BundleContext], _: Uri.Path) => {
      sendBackTo = sender

      //val asrc = resource.asInstanceOf[AsyncListResource[T]]
      resource.setActorContext(context)
      //log info s"calling ${asrc} with sender '${self}'"
      log debug s"delegating to ${resource.getClass.getSimpleName}#get('${self}')"
      log debug s""
      resource.get(self)

      become(out)
      //      val t = resourceController.get().asInstanceOf[List[T]]
      //      println("GET"+t)
      //
      //      //implicit val ec = context.system.dispatcher
      //
      //      implicit val formats = DefaultFormats
      //      implicit val serialization = jackson.Serialization
      //
      //      val m = Marshal(t).to[RequestEntity]
      //
      //      m.onSuccess {
      //        case value =>
      //          val reqEvent = RequestEvent(skysailContext, null)
      //          val resEvent = ResponseEvent(reqEvent, null)
      //          sendBackTo ! resEvent.copy(resource = t, httpResponse = resEvent.httpResponse.copy(entity = value))
      //      }

    }
    case SkysailContext(_: RequestContext, ApplicationModel(_, _, _, _), resource: AsyncEntityResource[_], _: Option[BundleContext], _: Uri.Path) => {
      sendBackTo = sender
      resource.setActorContext(context)
      resource.get(self)
      become(out)
    }
    case SkysailContext(_: RequestContext, ApplicationModel(_, _, _, _), resource: AsyncPostResource[_], _: Option[BundleContext], _: Uri.Path) => {
      sendBackTo = sender
      resource.setActorContext(context)
      resource.get(self)
      become(out)
    }
    case SkysailContext(_: RequestContext, ApplicationModel(_, _, _, _), resource: AsyncStaticResource, _: Option[BundleContext], _: Uri.Path) => {
      sendBackTo = sender
      resource.setActorContext(context)
      resource.get(self)
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
    case msg: T => {
      println("MSG: " + msg)
      val reqEvent = RequestEvent(null, null)
      val resEvent = ResponseEvent(reqEvent, null)
      sendBackTo ! resEvent.copy(httpResponse = resEvent.httpResponse.copy(entity = HttpEntity(msg.toString)))
    }
    case msg: Any => log info s"OUT: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

}
