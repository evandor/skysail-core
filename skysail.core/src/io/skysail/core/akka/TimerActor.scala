package io.skysail.core.akka

import akka.actor.ActorRef
import akka.actor.Actor
import java.util.Date
import akka.actor.Props
import org.slf4j.LoggerFactory
import akka.http.scaladsl.model.HttpResponse

//object TimerActor {
//  def props(nextActor: ActorRef) = Props(new TimerActor(nextActor))
//  //case class RequestEvent(sender: ActorRef, response: HttpResponse)
//}
//
//class TimerActor(nextActor: ActorRef) extends Actor {
//  
//  private val log = LoggerFactory.getLogger(this.getClass)
//  
//  def receive = stopped
//  
//  val startingAt = System.currentTimeMillis()
//  var stoppingAt = System.currentTimeMillis()
//  
//  def stopped: Actor.Receive = {
//    case RequestEvent(_, _) => {
//      log info "received request event in state 'start'"
//      context.become(started)
//      nextActor ! TimerActor.RequestEvent(sender, HttpResponse(200))
//    }
//    case _ => log warn "did not understand message"
//  }
//  
//  def started: Receive = {
//    case RequestEvent(_, _) => {
//      log info "received request event in state 'running'"
//      stoppingAt = System.currentTimeMillis()
//      //context.become(running)
//      nextActor ! RequestEvent(sender, HttpResponse(201))
//    }
//  }
//}