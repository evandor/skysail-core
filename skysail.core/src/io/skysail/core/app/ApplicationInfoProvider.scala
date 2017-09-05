package io.skysail.core.app

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.model.ApplicationModel
import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext
import io.skysail.core.app.menus.MenuItem

trait ApplicationProvider {
  def appModel(): ApplicationModel
  def routes(): List[(String, Class[_ <: io.skysail.core.resources.Resource[_]])]
  def getBundleContext(): Option[BundleContext] 
  def application(): SkysailApplication
  def menu(): Option[MenuItem] = None
}