package io.skysail.core.app

import io.skysail.core.resources.Resource
import io.skysail.core.resources.Resource._
import scala.reflect.runtime.universe._

case class RouteMapping[T: TypeTag](path: String, resourceClass: Class[_ <: Resource[T]]) {

  def getEntityType(): Type = resourceClass.newInstance().getType()

}