//package io.skysail.core.akka
//
//import akka.testkit.{ TestKit, ImplicitSender }
//import akka.actor.{ Props, Actor, ActorSystem }
//import org.scalatest.WordSpecLike
//
//import akka.util.Timeout
//import scala.concurrent.Await
//import scala.util.{ Success, Failure }
//
//import scala.language.postfixOps
//import akka.http.scaladsl.model.HttpResponse
//import org.slf4j.LoggerFactory
//
//class TimerActorSpec extends TestKit(ActorSystem("testsystem"))
//    with WordSpecLike
//    with ImplicitSender
//    with StopSystemAfterAll {
//
//  private val log = LoggerFactory.getLogger(this.getClass)
//
//  "A TimerActor" must {
//    "must start its timer when it receives a message the first time" in {
//
//      import akka.pattern.ask
//      import scala.concurrent.duration._
//      implicit val timeout = Timeout(3 seconds)
//      implicit val ec = system.dispatcher
//
//      val timerActorProps = TimerActor.props(testActor)
//      val filter = system.actorOf(timerActorProps, "timer")
//
//      val future = filter ? TimerActor.RequestEvent(null, HttpResponse(200))
//      future.onComplete {
//        case Failure(_) => log error "failure"
//        case Success(msg) => log info "success: " + msg
//      }
//
//      Await.ready(future, timeout.duration)
//    }
//
//  }
//}
