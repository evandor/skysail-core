package io.skysail.core.akka

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.RequestEntity
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{LinkRelation, ResourceAssociationType}

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag
import io.skysail.core.server.actors.ApplicationActor.SkysailContext
import org.json4s.{DefaultFormats, jackson}
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._

object ControllerActor {
  case class GetRequest()  
  case class PostRequest()
  case class PutRequest()
  case class DeleteRequest()  
}

class ControllerActor[T](resourceController: ResourceController[_]) extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  //val chainRoot: Props
  val originalSender = sender
  var sendBackTo: ActorRef = null

  var chainRootActor: ActorRef = null

  def receive = in

  import context._

  // TODO move down to subcontrollers? Not needed on AssetsController for example
  //protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit

  def in: Receive = LoggingReceive {
   // case gr: ControllerActor.GetRequest => get(sender)
    //case reqCtx: RequestContext => {
    case skysailContext: SkysailContext => {
      log debug "in... " + skysailContext.ctx
      sendBackTo = sender
      //chainRootActor = context.actorOf(chainRoot, "RequestProcessingActor")
      //chainRootActor ! (skysailContext, this.self)
      val t = resourceController.get().asInstanceOf[List[T]]
      println("GET"+t)

      //implicit val ec = context.system.dispatcher

      implicit val formats = DefaultFormats
      implicit val serialization = jackson.Serialization

      val m = Marshal(t).to[RequestEntity]

      m.onSuccess {
        case value =>
          val reqEvent = RequestEvent(skysailContext, null)
          val resEvent = ResponseEvent(reqEvent, null)
          sendBackTo ! resEvent.copy(resource = t, httpResponse = resEvent.httpResponse.copy(entity = value))
      }

     // become(out)
    }
  }

  def out: Receive = LoggingReceive {
   // case gr: ControllerActor.GetRequest => get(sender)
    case res:ResponseEvent[_] => {
      sendBackTo ! res
      context.stop(chainRootActor)
      become(in)
    }
  }

  override def preRestart(reason: Throwable, message: Option[Any]) {
    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
  }

}
