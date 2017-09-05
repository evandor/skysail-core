package io.skysail.core.app

import java.util.Dictionary

import org.osgi.service.cm.ManagedService
import org.osgi.service.component.ComponentContext
import org.osgi.service.component.annotations._

import akka.actor.ActorSystem
import io.skysail.core.app.resources.AppResource
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import io.skysail.core.app.resources._
import io.skysail.core.resources.AssetsResource
import io.skysail.core.app.resources.BundlesResource
import io.skysail.core.app.menus.MenuItem

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

@Component(
  immediate = true,
  property = { Array("service.pid=landingpages") },
  service = Array(classOf[ApplicationProvider], classOf[ManagedService]))
class SkysailRootApplication extends SkysailApplication(SkysailRootApplication.ROOT_APPLICATION_NAME, null, "backend root")
    with ApplicationProvider
    with ManagedService {

  var properties: Dictionary[String, _] = null
  def updated(props: Dictionary[String, _]): Unit = this.properties = props

  override def menu() = {
    Some(MenuItem("Root App", "fa-file-o", None, Some(List(
      MenuItem("Bundles", "fa-file-o", Some("/client/bundles")),
      MenuItem("Services", "fa-file-o", Some("/client/services"))))))
  }

  def routesMappings: List[(String, Class[_ <: io.skysail.core.resources.Resource[_]])] = {
    List(
      //"/login" -> classOf[AkkaLoginResource[String]],
      "/apps" -> classOf[AppsResource],
      "/apps/menus" -> classOf[MenusResource],
      "/bundles" -> classOf[BundlesResource],
      "/app" -> classOf[AppResource],
      "/assets" -> classOf[AssetsResource],
      "/user" -> classOf[CurrentUserController])
  }

}  


