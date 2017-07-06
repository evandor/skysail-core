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
import org.slf4j.LoggerFactory
import akka.pattern.ask
import scala.concurrent.duration._

class RequestProcessingActorSpec extends TestKit(ActorSystem("testsystem"))
    with WordSpecLike
    with ImplicitSender
    with StopSystemAfterAll {

  private val log = LoggerFactory.getLogger(this.getClass)

  //  "A RequestProcessingActor" must {
  //    "must start its timer when it receives a message the first time" in {
  //
  //      implicit val timeout = Timeout(3 seconds)
  //      implicit val ec = system.dispatcher
  //
  //      val nextActor = system.actorOf(Props[EchoActor2])
  //
  //      val props = RequestProcessingActor.then(classOf[EchoActor2])
  //      val rpa = system.actorOf(props, "requestProcessing")
  //
  //      val future = rpa ? HttpRequest() //RequestEvent(null,HttpResponse(200))
  //      future.onComplete {
  //        case Failure(_) => log warn "failure"
  //        case Success(msg) => log info "success: " + msg
  //      }
  //
  //      Await.ready(future, timeout.duration)
  //    }
  //  }

  "A RequestProcessingActor2" must {
    "must start its timer when it receives a message the first time2" in {

      implicit val timeout = Timeout(3 seconds)
      implicit val ec = system.dispatcher

      val worker = system.actorOf(Props[Worker])
      val theTimer = system.actorOf(Timer.props(worker))
      //val props = RequestProcessingActor.then(theTimer)
      val rpa = system.actorOf(RequestProcessingActor.then(theTimer))

      val future = rpa ? HttpRequest()
      future.onComplete {
        case Failure(_) => log warn "failure"
        case Success(msg) => log info "success: " + msg
      }

      Await.ready(future, timeout.duration)
    }
  }
}

class EchoActor2() extends Actor {

  private val log = LoggerFactory.getLogger(this.getClass)

  def receive = {
    //    case RequestEvent(_, _) => {
    //      log info "received request event in state 'start'"
    //      nextActor ! RequestEvent(sender, HttpResponse(200))
    //    }
    case re: RequestEvent2 => {
      log info "received request event in state 'start'"
      re.sender ! "hi" //RequestEvent(sender, HttpResponse(200))
    }
    case _ => log info "hier"
  }

}
