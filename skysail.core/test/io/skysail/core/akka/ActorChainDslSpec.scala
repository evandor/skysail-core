package io.skysail.core.akka

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import io.skysail.core.akka.dsl.ActorChainDsl._
import org.hamcrest.CoreMatchers._
import org.junit.Assert._
import akka.testkit.TestKit
import akka.actor.ActorSystem
import akka.testkit.ImplicitSender
import org.junit.Assert.assertThat
import io.skysail.core.akka.actors.Timer

//@RunWith(classOf[JUnitRunner])
class ActorChainDslSpec extends TestKit(ActorSystem("testsystem"))
    with WordSpecLike
    with ImplicitSender
    with StopSystemAfterAll {

  "A chain of two Actors" must {
    
    "have the size 2" in {
      val chain = classOf[RequestProcessingActor[_]] ==> classOf[Timer]
      assertThat(chain.elems.size, is(2))
    }

    "create the first actor with a reference to the second" in {
      val chain = classOf[RequestProcessingActor[_]] ==> classOf[Timer]
      val rootActor = chain.build()
      assertTrue(rootActor != null)
    }
  }

  //  "A chain of three Actors" should "have the size 3" in {
  //    val chain = classOf[RequestProcessingActor[_]] ==> classOf[Timer] ==> classOf[Timer]
  //    assertThat(chain.elems.size, is(3))
  //  }
  //
  //  "A chain of four Actors" should "have the size 4" in {
  //    val chain = classOf[RequestProcessingActor[_]] ==> classOf[Timer] ==> classOf[Timer] ==> classOf[Timer]
  //    assertThat(chain.elems.size, is(4))
  //  }
  //
  //  "A" should "B" in {
  //    val chain = classOf[RequestProcessingActor[_]] ==> classOf[Timer]
  //    val entry = chain.build()
  //  }
}