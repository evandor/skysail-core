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
  //case class Request()
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
  //val nextActor = context.actorOf(Props[TimerActor])
  val originalSender = sender
  def receive = {
    //case m: Res => originalSender ! "xxx"
    //case _ => nextActor ! Req("xxx")
    case GetListEvent(backTo, response) => backTo ! "hi20" + response.msg
    case _ => nextActor ! GetListEvent(sender, Response())
    //case _ => sender ! "hi3"
  }
}

class DataExtractingActor() extends Actor {
  def receive =  {
    //case m:Req => sender ! Res("hi2")
    //case _ => sender ! "hi2"
    case GetListEvent(backTo,response) => sender ! GetListEvent(backTo, response.copy(msg = "hi77"))
    case _ => throw new IllegalArgumentException()
  }
}

object MyActor {
  case class Greeting(from: String)
  case object Goodbye
}
class MyActor extends Actor with ActorLogging {
  import MyActor._
  def receive = {
    case Greeting(greeter) => log.info(s"I was greeted by $greeter.")
    case Goodbye           => log.info("Someone said goodbye to me.")
  }
}