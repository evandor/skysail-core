package io.skysail.core.akka.actors

import akka.actor.{Actor, ActorIdentity, ActorRef, ActorSystem, Identify}
import io.skysail.core.akka.actors.CounterActor._
import scala.concurrent.duration.FiniteDuration
import akka.util.Timeout

object CounterActor {
  case class CountRequest(requestId : String, timeout : FiniteDuration /*Timeout*/)
  case class FinishCounting(requestId : String, originalSender : ActorRef)
  case class CountResponse(requestId : String, count : Int)
}

// https://stackoverflow.com/questions/27445955/akka-get-total-number-of-actors
class CounterActor extends Actor {
  var counters = Map[String, Int]()

  implicit val system = ActorSystem()

  def receive = {
    case CountRequest(requestId, timeout) =>
      counters = counters.updated(requestId, 0)
      system.actorSelection("/user/*") ! Identify(requestId)
      //system.scheduler.scheduleOnce(timeout, FinishCounting(requestId, sender()), self)

    case ActorIdentity(cId, ref) =>
      //counters = counters.updated(cId, counters.getOrElse(cId, 0) + 1)
      //system.actorSelection(ref.path() / "*") ! Identify(cId)

    case FinishCounting(requestId, originalSender) =>
      originalSender ! CountResponse(requestId, counters.getOrElse(requestId, 0))
  }
}