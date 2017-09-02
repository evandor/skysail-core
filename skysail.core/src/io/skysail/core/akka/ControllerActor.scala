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
import io.skysail.core.akka.actors.AsyncListResource

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import org.json4s.{ jackson, native, DefaultFormats }
import scala.util.Success
import scala.util.Failure

object ControllerActor {
  case class GetRequest()
  case class PostRequest()
  case class PutRequest()
  case class DeleteRequest()
}

class ControllerActor[T](resource: Resource[_]) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  log debug s"new ControllerActor created -> Resource('${resource}')"

  val originalSender = sender
  var sendBackTo: ActorRef = null

  import context._

  def receive = in

  def in: Receive = LoggingReceive {
    // case gr: ControllerActor.GetRequest => get(sender)
    //case reqCtx: RequestContext => {
    case skysailContext: SkysailContext => {
      log debug s"IN - self:   ${self}"
      log debug s"IN - sender: ${sender}"
      log debug ""
      sendBackTo = sender

      val asrc = resource.asInstanceOf[AsyncListResource[T]]
      asrc.setActorContext(context)
      //log info s"calling ${asrc} with sender '${self}'"
      log debug s"delegating to ${asrc.getClass.getSimpleName}#get('${self}')"
      log debug s""
      asrc.get(self)

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
    case msg: Any => log info s"IN: received unknown message '$msg' in ${this.getClass.getName}"
  }

  def out: Receive = LoggingReceive {
    case msg: List[T] => {
      log debug s"OUT - self:   ${self}"
      log debug s"OUT - sender: ${sender}"
      log debug ""
      //implicit val ec = context.system.dispatcher
      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization
      val m = Marshal(msg).to[RequestEntity]
      m.onSuccess {
        case value =>
          val reqEvent = RequestEvent(null, null)
          val resEvent = ResponseEvent(reqEvent, null)
          log debug s"backto: ${sendBackTo} ! ${resEvent}"; 
          sendBackTo ! resEvent.copy(resource = msg, httpResponse = resEvent.httpResponse.copy(entity = value))
      }
    }
    case msg: Any => log info s"OUT: received unknown message '$msg' in ${this.getClass.getName}"
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

}
