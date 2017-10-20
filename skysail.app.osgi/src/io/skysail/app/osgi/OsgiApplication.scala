package io.skysail.app.osgi

import io.skysail.app.osgi.OsgiApplication._
import io.skysail.core.app.{ApiVersion, ApplicationProvider, RouteMapping, SkysailApplication}

object OsgiApplication {
  val APPLICATION_NAME = "osgi"
  val API_VERSION = ApiVersion(1)
}

class OsgiApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail OSGi Application") with ApplicationProvider {

//  override def menu() = {
//    Some(
//      MenuItem("DemoApp", "fa-file-o", None, Some(List(
//        MenuItem("Config", "fa-plus", Some("/client/demo/v1/configs")),
//        MenuItem("ElasticSearch", "fa-plus", Some("/client/demo/v1/indices")),
//        MenuItem("Contacts", "fa-user", None, Some(List(
//          MenuItem("add Contact", "fa-plus", Some("/client/demo/v1/contacts/new"))
//        )))
//      ))))
//  }

  override def routesMappings = List(
    RouteMapping("/bundles", classOf[BundlesResource]),
    RouteMapping("/bundlestop/:id", classOf[StopBundleResource]), // wait for pathmatcherFactory fix
    RouteMapping("/bundlestart/:id", classOf[StartBundleResource]), // wait for pathmatcherFactory fix
    RouteMapping("/bundles/:id", classOf[BundleResource]),
    RouteMapping("/services", classOf[ServicesResource])
  )

}