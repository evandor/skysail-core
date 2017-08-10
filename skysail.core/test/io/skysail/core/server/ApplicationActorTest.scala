package io.skysail.core.server

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.RequestContext
import akka.pattern.ask
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import io.skysail.core.akka.actors.{Delegator, Timer}
import io.skysail.core.akka.{RequestProcessingActor, StopSystemAfterAll}
import io.skysail.core.dsl.ActorChainDsl._
import io.skysail.core.model.ApplicationModel
import org.mockito.Mockito
import org.scalatest.WordSpecLike

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}

class ApplicationActorTest  extends TestKit(ActorSystem("testsystem"))
  with WordSpecLike
  with ImplicitSender
  with StopSystemAfterAll {

  implicit val timeout = Timeout(3 seconds)
  implicit val ec = system.dispatcher

  "An ApplicationActor" must {
    
    "provide its applicationModel for a GetAppModel message" in {

      val appModel = Mockito.mock(classOf[ApplicationModel])
      val appActor = system.actorOf(Props.apply(classOf[ApplicationActor], appModel))
      appActor ! ApplicationActor.GetAppModel()
      expectMsg(appModel)

//      future.onComplete {
//        case Failure(_) => println("failure")
//        case Success(msg) => println("success: " + msg)
//      }
      //Await.ready(future, timeout.duration)
    }
  }
}
