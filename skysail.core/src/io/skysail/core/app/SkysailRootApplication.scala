package io.skysail.core.app

import java.util.Dictionary

import org.osgi.service.cm.ManagedService
import org.osgi.service.component.ComponentContext
import org.osgi.service.component.annotations._

import akka.actor.ActorSystem
import io.skysail.core.app.resources.AkkaLoginResource
import io.skysail.core.app.resources.AppListResource
import io.skysail.core.app.resources.AppResource
import io.skysail.core.app.resources.DefaultResource3
import io.skysail.core.restlet.services.ResourceBundleProvider
import io.skysail.core.app.resources.DefaultResource2
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http

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
  service = Array(classOf[ApplicationRoutesProvider], classOf[ResourceBundleProvider], classOf[ManagedService]))
class SkysailRootApplication extends SkysailApplication(SkysailRootApplication.ROOT_APPLICATION_NAME, null)
    //with ApplicationProvider
    with ApplicationRoutesProvider
    with ResourceBundleProvider
    with ManagedService {

  var properties: Dictionary[String, _] = null

  def updated(props: Dictionary[String, _]): Unit = this.properties = props

  //  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
  //  def setActorSystem(as: ActorSystem) = this.system = as
  //  def unsetActorSystem(as: ActorSystem) = this.system = null

  def routesMappings: List[(String, Class[_ <: io.skysail.core.akka.ResourceActor[_]])] = {
    List(
      "first" -> classOf[DefaultResource2],
      "second" -> classOf[DefaultResource3[String]],
      "login" -> classOf[AkkaLoginResource[String]],
      "appList" -> classOf[AppListResource],
      "app" -> classOf[AppResource])
  }

  def dummyPath(appPath: String): Route = {
    path(appPath) {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http2</h1>"))
      }
    }

  }

}  


