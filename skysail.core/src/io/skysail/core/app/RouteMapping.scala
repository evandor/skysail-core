package io.skysail.core.app

import io.skysail.api.ddd.DddElement
import io.skysail.core.resources.Resource
import io.skysail.core.resources.Resource._

import scala.reflect.runtime.universe._

case class RouteMapping[T /*<: DddElement*/: TypeTag](path: String, resourceClass: Class[_ <: Resource[T]]) {

  def getEntityType(): Type = resourceClass.newInstance().getType()

}