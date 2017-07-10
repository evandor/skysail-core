package io.skysail.core.akka

import io.skysail.core.model.LinkRelation
import org.restlet.data.Method
import io.skysail.core.model.ResourceAssociationType
import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.Actor._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import java.util.Date

case class Req(msg: String)

case class Res(msg: String)


abstract class ResourceDefinition[T] extends Actor with ActorLogging {

  def getLinkRelation() = LinkRelation.CANONICAL

  def getVerbs(): Set[Method] = Set()

  def linkedResourceClasses(): List[Class[_ <: ResourceDefinition[_]]] = List()

  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceDefinition[_]]]]()

  val chainRoot: ActorRef

  implicit val system = ActorSystem()
  val nextActor: ActorRef = null
  //context.actorOf(Props[DataExtractingActor])
  val originalSender = sender

  var sendBackTo: ActorRef = null

  def receive = in

  import context._

  def in: Receive = {
    case e => {
      println("in... " + e)
      sendBackTo = sender
      chainRoot ! e
      become(out)
    }
  }

  def out: Receive = {
    case e => {
      println("out... " + e)
      sendBackTo ! e
      log info "stopping actor: " + chainRoot
      context.stop(chainRoot)
      become(in)
    }
  }


}
