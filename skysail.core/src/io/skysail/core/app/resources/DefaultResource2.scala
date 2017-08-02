//package io.skysail.core.app.resources
//
//import akka.actor.Actor
//import akka.actor.ActorRef
//import akka.actor.Props
//import io.skysail.core.akka.ResourceActor
//import io.skysail.core.app.resources.DefaultResource2._
//
//object DefaultResource2 {
//  case class Response(msg: String = "")
//  case class GetListEvent(sender: ActorRef, response: Response)
//}
//
//class DefaultResource2[String] extends ResourceActor[String] {
//  
//  override val nextActor = context.actorOf(Props[DataExtractingActor])
//  
//  override def receive = {
//    case GetListEvent(backTo, response) => backTo ! "hi20" + response.msg
//    case _ => nextActor ! GetListEvent(sender, Response())
//  }
//
//  override val chainRoot: ActorRef = null
//
//  def get(): String = {
//    ???
//  }
//
//}
//
//class DataExtractingActor() extends Actor {
//  def receive = {
//    case GetListEvent(backTo, response) => sender ! GetListEvent(backTo, response.copy(msg = "hi77"))
//    case _ => throw new IllegalArgumentException()
//  }
//}
//
