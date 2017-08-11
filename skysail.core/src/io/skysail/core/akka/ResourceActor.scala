package io.skysail.core.akka

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{LinkRelation, ResourceAssociationType}

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag

object ResourceActor {
  case class GetRequest()  
  case class PostRequest()
  case class PutRequest()
  case class DeleteRequest()  
}

abstract class ResourceActor[T] extends Actor with ActorLogging {

  implicit val askTimeout: Timeout = 1.seconds

  val chainRoot: Props
  val originalSender = sender
  var sendBackTo: ActorRef = null

  var chainRootActor: ActorRef = null

  def receive = in

  import context._

  protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit

  def in: Receive = LoggingReceive {
    case gr: ResourceActor.GetRequest => get(sender)
    case reqCtx: RequestContext => {
      log debug "in... " + reqCtx
      sendBackTo = sender
      chainRootActor = context.actorOf(chainRoot, "RequestProcessingActor")
      chainRootActor ! (reqCtx, this.self)
      become(out)
    }
  }

  def out: Receive = LoggingReceive {
    case gr: ResourceActor.GetRequest => get(sender)
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
