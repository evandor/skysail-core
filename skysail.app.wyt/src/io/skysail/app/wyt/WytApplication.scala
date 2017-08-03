package io.skysail.app.wyt;

import org.osgi.service.component._
import org.osgi.service.component.annotations._
import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route
import io.skysail.app.wyt.WytApplication._
import io.skysail.core.app._

object WytApplication {
  val APPLICATION_NAME = "wyt"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=wyt") }, service = Array(classOf[ApplicationRoutesProvider]))
class WytApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION) with ApplicationRoutesProvider {

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
  def setActorSystem(as: ActorSystem) = this.system = as
  def unsetActorSystem(as: ActorSystem) = this.system = null

  override def routesMappings = List(
    "pacts" -> classOf[PactsResource])

  def dummyPath(appPath: String): Route = {
    path(appPath) {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http2</h1>"))
      }
    }

  }
}