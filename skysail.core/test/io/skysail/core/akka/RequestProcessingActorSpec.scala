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

import io.skysail.core.akka.dsl.ActorChainDsl._
import akka.http.scaladsl.server.RequestContext

import org.mockito.Mockito
import org.mockito.Mockito._
import io.skysail.core.akka.actors.Timer
import io.skysail.core.akka.actors.Delegator
import io.skysail.core.akka.actors.Worker

class RequestProcessingActorSpec extends TestKit(ActorSystem("testsystem"))
    with WordSpecLike
    with ImplicitSender
    with StopSystemAfterAll {

  implicit val timeout = Timeout(3 seconds)
  implicit val ec = system.dispatcher

  "A RequestProcessingActor" must {
    "initiate a request/reponse cycle" in {
      
      val chain = classOf[RequestProcessingActor[_]] ==> 
                    classOf[Timer] ==> 
                    classOf[Delegator] ==> 
                    classOf[Worker]

      val ctxMock = Mockito.mock(classOf[RequestContext])
      Mockito.when(ctxMock.request).thenReturn(HttpRequest())
                    
      val future = chain.build() ? ctxMock//HttpRequest()

      future.onComplete {
        case Failure(_) => println("failure")
        case Success(msg) => println("success: " + msg)
      }

      Await.ready(future, timeout.duration)
    }
  }
}
