package io.skysail.core.app

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.model.ApplicationModel

trait ApplicationInfoProvider {
  def appModel(): ApplicationModel
  def routes(): List[(PathMatcher[Unit], Class[_ <: io.skysail.core.akka.ResourceActor[_]])]
}