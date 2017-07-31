package io.skysail.core.akka

import io.skysail.core.model.LinkRelation
import io.skysail.core.model.ResourceAssociationType
import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date
import akka.actor.ActorDSL
import akka.actor.ActorDSL
import io.skysail.core.akka.dsl.ActorChainDsl
import io.skysail.core.akka.dsl.ActorChainDsl
import io.skysail.core.akka.dsl.ActorChainDsl.ActorChain

abstract class ResourceActor[T] extends Actor with ActorLogging {

  def getLinkRelation() = LinkRelation.CANONICAL
  //def getVerbs(): Set[Method] = Set()
  def linkedResourceClasses(): List[Class[_ <: ResourceActor[_]]] = List()
  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceActor[_]]]]()
  val chainRoot: ActorRef
  //val chain: ActorChainDsl.ActorChain[_]

  implicit val system = ActorSystem()
  val nextActor: ActorRef = null
  val originalSender = sender
  var sendBackTo: ActorRef = null
  def receive = in

  import context._
  
  def get(): T

  def in: Receive = {
    case e => {
      log info "in... " + e
      sendBackTo = sender
      import io.skysail.core.akka.dsl.ActorChainDsl._

      // log info s"MESSAGE: ${chainRoot} ! (${e},${this}"
      chainRoot ! (e, this)
      become(out)
    }
  }

  def out: Receive = {
    case e => {
      log info "out... " + e
      log info "sending to " + sendBackTo
      sendBackTo ! e
      log info "stopping actor: " + chainRoot
      context.stop(chainRoot)
      become(in)
    }
  }


}
