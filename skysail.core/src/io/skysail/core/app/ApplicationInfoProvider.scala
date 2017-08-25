package io.skysail.core.app

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.model.ApplicationModel
import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext

trait ApplicationInfoProvider {
  def appModel(): ApplicationModel
  def routes(): List[(PathMatcher[Unit], Class[_ <: io.skysail.core.akka.ResourceController[_]])]
  def getBundleContext(): Option[BundleContext] 
}