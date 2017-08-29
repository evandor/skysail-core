package io.skysail.core.server

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import io.skysail.core.akka.StopSystemAfterAll
import io.skysail.core.model.ApplicationModel
import org.mockito.Mockito
import org.scalatest.WordSpecLike

import scala.concurrent.duration._
import scala.language.postfixOps
import io.skysail.core.server.actors.ApplicationActor

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
