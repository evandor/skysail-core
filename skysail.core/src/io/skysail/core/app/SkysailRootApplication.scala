package io.skysail.core.app

import java.util.Dictionary

import org.osgi.service.cm.ManagedService
import org.osgi.service.component.ComponentContext
import org.osgi.service.component.annotations._

import akka.actor.ActorSystem
import io.skysail.core.app.resources.AkkaLoginResource
import io.skysail.core.app.resources.AppResource
import io.skysail.core.app.resources.DefaultResource2
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import io.skysail.core.app.resources._
import io.skysail.core.akka.actors.AssetsController
import io.skysail.core.app.resources.BundlesController
import io.skysail.core.akka.actors.BackendIndexController

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
  service = Array(classOf[ApplicationInfoProvider], classOf[ManagedService]))
class SkysailRootApplication extends SkysailApplication(SkysailRootApplication.ROOT_APPLICATION_NAME, null)
    with ApplicationInfoProvider
    with ManagedService {

  var properties: Dictionary[String, _] = null
  def updated(props: Dictionary[String, _]): Unit = this.properties = props

  def routesMappings: List[(String, Class[_ <: io.skysail.core.akka.ResourceController[_]])] = {
    List(
      "login" -> classOf[AkkaLoginResource[String]],
      "apps" -> classOf[AppsController],
      "bundles" -> classOf[BundlesController],
      "app" -> classOf[AppResource],
      "assets" -> classOf[AssetsController],
      "" -> classOf[BackendIndexController]
        
    )
  }

}  


