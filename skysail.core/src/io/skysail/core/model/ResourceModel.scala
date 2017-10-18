package io.skysail.core.model

import io.skysail.core.app.RouteMapping
import org.slf4j.LoggerFactory

import scala.reflect.runtime.universe._

/**
  * A ResourceModel connects a pathDefinition with a ResourceController class
  * which will handle specific requests to this path.
  *
  * The HTTP verbs which will be handled depend on the concrete subclass of the ResourceController provided.
  *
  * @param routeMapping todo
  */
case class ResourceModel(routeMapping: RouteMapping[_]) {

  require(routeMapping.path != null, "A ResourceModel's pathMatcher must not be null")
  require(routeMapping.resourceClass != null, "A ResourceModel's resource class must not be null")

  private val log = LoggerFactory.getLogger(this.getClass())

  val entityClass: Type = routeMapping.getEntityType()
  var linkModel: LinkModel2 = _
  var linkModels: List[LinkModel2] = List()
  var entityModel = new EntityModel(entityClass)

  def linkFor(clsName: String): Option[String] = {
    if (routeMapping.resourceClass.getName == clsName) Some(routeMapping.path) else None
  }

  def resourceType() = {
    //    resource match {
    //      case _: ListServerResource[_] => LIST_RESOURCE
    //      case _: EntityServerResource[_] => ENTITY_RESOURCE
    //      case _: PutEntityServerResource[_] => UPDATE_ENTITY_RESOURCE
    //      case _: PostEntityServerResource[_] => CREATE_ENTITY_RESOURCE
    //      case _ => UNSPECIFIED_RESOURCE
    //    }
    LIST_RESOURCE
  }

  private def printList(list: List[_]) = list.map(v => v).mkString("")

  private def getPathVariables(path: String) =
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map {
        (e => e.toString().replace("{", "").replace("}", ""))
      }
      .toList

}