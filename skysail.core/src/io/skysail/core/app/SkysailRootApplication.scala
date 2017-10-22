package io.skysail.core.app

import java.util.Dictionary

import akka.http.scaladsl.server.{PathMatcher, PathMatchers}
import io.skysail.core.app.menus.MenuItem
import io.skysail.core.app.resources.{AppResource, BundlesResource, _}
import io.skysail.core.resources.AssetsResource
import org.osgi.service.cm.ManagedService

object SkysailRootApplication {
  val ROOT_APPLICATION_NAME = "root"

  val LOGIN_PATH = "/_login"
  val LOGIN_CALLBACK = "/_logincallback"
  val PROFILE_PATH = "/_profile"
  val PUPLIC_PATH = "/_public"
  val LOGOUT_PATH = "/_logout"

  val CONFIG_IDENTIFIER_LANDINGPAGE_NOT_AUTHENTICATED = "landingPage.notAuthenticated"
  val CONFIG_IDENTIFIER_LANDINGPAGE_AUTHENTICATED = "landingPage.authenticated"

}

//@Component(
//  immediate = true,
//  property = { Array("service.pid=landingpages") },
//  service = Array(classOf[ApplicationProvider], classOf[ManagedService]))
class SkysailRootApplication extends SkysailApplication(SkysailRootApplication.ROOT_APPLICATION_NAME, null, "backend root")
    with ApplicationProvider
    with ManagedService {

  var properties: Dictionary[String, _] = null
  def updated(props: Dictionary[String, _]): Unit = this.properties = props

  override def menu() = {
    Some(MenuItem("Root App", "fa-file-o", None, Some(List(
      //MenuItem("Bundles Persp.", "fa-file-o", Some("/bundles")),
      //MenuItem("Services Persp.", "fa-file-o", Some("/services")),
      MenuItem("Bundles", "fa-file-o", Some("/maincontent/bundles")),
      MenuItem("Services", "fa-file-o", Some("/maincontent/services"))
    ))))
  }

  def routesMappings: List[RouteMapping[_]] = {
    List(
      //"/login" -> classOf[AkkaLoginResource[String]],
      RouteMapping("/apps", classOf[AppsResource]),
      RouteMapping("/apps/menus", classOf[MenusResource]),
      RouteMapping("/bundles", classOf[BundlesResource]),
      RouteMapping("/bundlestop/:id", classOf[StopBundleResource]), // wait for pathmatcherFactory fix
      RouteMapping(null, classOf[BundleResource]).setPathMatcher(PathMatcher("bundles") / PathMatchers.Segment),
      RouteMapping("/bundlestart/:id", classOf[StartBundleResource]), // wait for pathmatcherFactory fix
      //RouteMapping("/bundles/:id", classOf[BundleResource]),
      RouteMapping("/services", classOf[ServicesResource]),
      RouteMapping("/app", classOf[AppResource]),
      RouteMapping("/assets", classOf[AssetsResource]),
      RouteMapping("/user", classOf[CurrentUserController]),
      RouteMapping("/system/load", classOf[LoadAverageResource])
    )
  }

}  


