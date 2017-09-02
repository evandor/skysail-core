package io.skysail.core.akka

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.LoggingReceive
import akka.http.scaladsl.server.RequestContext
import akka.util.Timeout
import io.skysail.core.model.{LinkRelation, ResourceAssociationType}

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag
import io.skysail.core.server.actors.ApplicationActor.SkysailContext

//object ResourceController {
//  case class GetRequest()  
//  case class PostRequest()
//  case class PutRequest()
//  case class DeleteRequest()  
//}

abstract class Resource[T] { //extends Actor with ActorLogging {

  //implicit val askTimeout: Timeout = 1.seconds

  //val chainRoot: Props
 // val originalSender = sender
  var sendBackTo: ActorRef = null

 // var chainRootActor: ActorRef = null

 // def receive = in

 // import context._

  // TODO move down to subcontrollers? Not needed on AssetsController for example
  //def get(): T

//  def in: Receive = LoggingReceive {
//    case gr: ResourceController.GetRequest => get(sender)
//    //case reqCtx: RequestContext => {
//    case skysailContext: SkysailContext => {
//      log debug "in... " + skysailContext.ctx
//      sendBackTo = sender
//      chainRootActor = context.actorOf(chainRoot, "RequestProcessingActor")
//      chainRootActor ! (skysailContext, this.self)
//      become(out)
//    }
//  }

//  def out: Receive = LoggingReceive {
//    case gr: ResourceController.GetRequest => get(sender)
//    case res:ResponseEvent[_] => {
//      sendBackTo ! res
//      context.stop(chainRootActor)
//      become(in)
//    }
//  }

//  override def preRestart(reason: Throwable, message: Option[Any]) {
//    log.error(reason, "Restarting due to [{}] when processing [{}]", reason.getMessage, message.getOrElse(""))
//  }

}
