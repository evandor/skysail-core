//package io.skysail.core.akka
//
//import akka.actor.ActorRef
//import akka.actor.Actor
//import java.util.Date
//import akka.actor.Props
//import org.slf4j.LoggerFactory
//import akka.http.scaladsl.model.HttpResponse
//
//object WorkerActor {
//  def props(nextActor: ActorRef) = Props(new TimerActor(nextActor))
//  case class RequestEvent(sender: ActorRef, response: HttpResponse)
//}
//
//class WorkerActor(nextActor: ActorRef) extends Actor {
//  
//  private val log = LoggerFactory.getLogger(this.getClass)
//    
//  def receive = {
//    case RequestEvent(_, _) => {
//      log info "received request event in state 'start'"
//      nextActor ! RequestEvent(sender, HttpResponse(200))
//    }
//    case _ => log warn "did not understand message"
//  }
//  
//}