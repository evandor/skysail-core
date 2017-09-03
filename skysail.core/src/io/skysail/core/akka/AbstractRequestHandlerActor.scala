//package io.skysail.core.akka
//
//import akka.actor._
//import akka.actor.Actor._
//import java.util.Date
//import akka.http.scaladsl.model.HttpRequest
//import akka.util.Timeout
//import scala.concurrent.duration.DurationInt
//
//abstract class AbstractRequestHandlerActor extends Actor with ActorLogging {
//
//  var returnTo: ActorRef = null
//
//  implicit val askTimeout: Timeout = 1.seconds
//  implicit val ec = context.system.dispatcher
//
//  def receive = {
//    case req: RequestEvent => receivedRequestEvent(req)
//    case res: ResponseEvent[_] => receivedResponseEvent(res)
//    case msg => log info s"unknown message of type '${msg.getClass.getName}' received"
//  }
//
//  def nextActorsProps(): Props
//  def doRequest(req: RequestEvent): Unit = {}
//  def doResponse(nextActor: ActorRef, res: ResponseEvent[_]): Unit = {}
//
//  protected def receivedRequestEvent(req: RequestEvent) = {
//    log debug s"RequestEvent received"
//    //log info s"setting returnTo to " + sender
//    returnTo = sender
//    doRequest(req)
//    if (nextActorsProps != null) {
//      val a = context.actorOf(nextActorsProps(), nextActorsProps().actorClass().getSimpleName)
//      //log info s"proceeding with " + a
//      a ! RequestEvent(req.ctx, req.resourceActor) //, req.response) // just "! req" ???
//    } else {
//      log debug s"returning to " + returnTo
//      val res = ResponseEvent(req, null)
//      //doResponse(res)
//      /*returnTo ! */ doResponse(returnTo, res)
//    }
//  }
//
//  protected def receivedResponseEvent(res: ResponseEvent[_]) = {
//    log debug s"ResponseEvent received"
//    //log info s"returning to $returnTo with $res"
//    /*returnTo ! */ doResponse(returnTo, res)
//  }
//
//}
//
//
//
//
//
