package io.skysail.core.akka

import io.skysail.core.model.LinkRelation
import org.restlet.data.Method
import io.skysail.core.model.ResourceAssociationType
import akka.actor.ActorLogging
import akka.actor.Actor

class ResourceDefinition[T] extends Actor with ActorLogging {
  
  def getLinkRelation() = LinkRelation.CANONICAL
  def getVerbs(): Set[Method] = Set()
  def linkedResourceClasses():List[Class[_ <: ResourceDefinition[_]]] = List()
  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceDefinition[_]]]]()

  def receive: Actor.Receive = {
    case _ => sender ! "hi"
  }
}