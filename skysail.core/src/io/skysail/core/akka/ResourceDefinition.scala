package io.skysail.core.akka

import io.skysail.core.model.LinkRelation
import org.restlet.data.Method
import io.skysail.core.model.ResourceAssociationType

class ResourceDefinition[T] {
  def getLinkRelation() = LinkRelation.CANONICAL
  def getVerbs(): Set[Method] = Set()
  def linkedResourceClasses():List[Class[_ <: ResourceDefinition[_]]] = List()
  val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: ResourceDefinition[_]]]]()
}