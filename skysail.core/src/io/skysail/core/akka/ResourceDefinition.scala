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
import io.skysail.core.akka.ResourceDefinition._
import java.util.Date

case class Req(msg: String)
case class Res(msg: String)

object ResourceDefinition {
  case class Response(msg: String = "")
  case class GetListEvent(sender: ActorRef, response: Response)
}

class ResourceDefinition[T] extends Actor with ActorLogging {
  
  def getLinkRelation() = LinkRelation.CANONICAL
  def getVerbs(): Set[Method] = Set()
  def linkedResourceClasses():List[Class[_ <: ResourceDefinition[_]]] = List()
  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceDefinition[_]]]]()

  implicit val system = ActorSystem() 
  val nextActor = context.actorOf(Props[DataExtractingActor])
  val originalSender = sender
  def receive = {
    case GetListEvent(backTo, response) => backTo ! "hi20" + response.msg
    case _ => nextActor ! GetListEvent(sender, Response())
  }
}

class DataExtractingActor() extends Actor {
  def receive =  {
    case GetListEvent(backTo,response) => sender ! GetListEvent(backTo, response.copy(msg = "hi77"))
    case _ => throw new IllegalArgumentException()
  }
}

