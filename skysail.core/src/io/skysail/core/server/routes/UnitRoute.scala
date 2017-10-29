package io.skysail.core.server.routes

import akka.http.scaladsl.server.PathMatcher

trait MyRoute {
  def count(): Int
}

case class UnitRoute(pathMatcher: PathMatcher[_]) extends MyRoute {
  override def count() = 0
}

case class StringRoute(pathMatcher: PathMatcher[_]) extends MyRoute {
  override def count() = 1
}
