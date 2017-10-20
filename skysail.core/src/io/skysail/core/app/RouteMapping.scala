package io.skysail.core.app

import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.resources.Resource
import io.skysail.core.resources.Resource._

import scala.reflect.runtime.universe._

case class RouteMapping[T /*<: DddElement*/: TypeTag](path: String, resourceClass: Class[_ <: Resource[T]]) {

  var pathMatcher: PathMatcher[_] = null

//  def this(pathMatcher: PathMatcher[_], resourceClass: Class[_ <: Resource[T]]) {
//    this(null, resourceClass)
//    this.pathMatcher = pathMatcher
//  }

  def setPathMatcher(pathMatcher: PathMatcher[_]): RouteMapping[T] = {
    this.pathMatcher = pathMatcher;
    this
  }

  def getEntityType(): Type = resourceClass.newInstance().getType()

}