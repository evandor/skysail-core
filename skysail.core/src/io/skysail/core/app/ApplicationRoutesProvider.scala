package io.skysail.core.app

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.PathMatcher

trait ApplicationRoutesProvider {
  //def routes(): List[Route]
  def routes2(): List[(PathMatcher[Unit], Class[_ <: io.skysail.core.akka.ResourceActor[_]])]
  def dummyPath(path: String):Route
}