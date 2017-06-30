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

case class Req(msg: String)
case class Res(msg: String)

class ResourceDefinition[T] extends Actor with ActorLogging {
  
  def getLinkRelation() = LinkRelation.CANONICAL
  def getVerbs(): Set[Method] = Set()
  def linkedResourceClasses():List[Class[_ <: ResourceDefinition[_]]] = List()
  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceDefinition[_]]]]()

  implicit val system = ActorSystem() 
  val nextActor = context.actorOf(Props[DataExtractingActor])
  val originalSender = sender
  def receive = {
    case m: Res => originalSender ! "xxx"
    case _ => nextActor ! Req("xxx")
  }
}

class DataExtractingActor() extends Actor {
  def receive =  {
    case m:Req => sender ! Res("hi2")
    //case _ => sender ! "hi2"
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