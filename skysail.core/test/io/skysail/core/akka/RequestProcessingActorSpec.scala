package io.skysail.core.akka

import akka.testkit.{ TestKit, ImplicitSender }
import akka.actor.{ Props, Actor, ActorSystem }
import org.scalatest.WordSpecLike

import akka.util.Timeout
import scala.concurrent.Await
import scala.util.{ Success, Failure }

import scala.language.postfixOps
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.HttpRequest
import akka.pattern.ask
import scala.concurrent.duration._

class RequestProcessingActorSpec extends TestKit(ActorSystem("testsystem"))
    with WordSpecLike
    with ImplicitSender
    with StopSystemAfterAll {

  implicit val timeout = Timeout(3 seconds)
  implicit val ec = system.dispatcher

  "A RequestProcessingActor" must {
    "initiate a request/reponse cycle" in {

      val worker = system.actorOf(Props[Worker], "worker")
      val delegator = system.actorOf(Delegator.props(worker), "delegator")
      val theTimer = system.actorOf(Timer.props(delegator), "timer")
      val rpa = system.actorOf(RequestProcessingActor.then(theTimer), "rpa")

      val future = rpa ? HttpRequest()

      future.onComplete {
        case Failure(_) => println("failure")
        case Success(msg) => println("success: " + msg)
      }

      Await.ready(future, timeout.duration)
    }
  }
}
