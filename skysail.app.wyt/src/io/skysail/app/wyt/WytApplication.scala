package io.skysail.app.wyt;

import org.osgi.service.component._
import org.osgi.service.component.annotations._
import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route
import io.skysail.app.wyt.WytApplication._
import io.skysail.core.app._
import io.skysail.core.app.ApplicationProvider

object WytApplication {
  val APPLICATION_NAME = "wyt"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=wyt") }, service = Array(classOf[ApplicationProvider]))
class WytApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Wait-your-turn backend") with ApplicationProvider {

  override def routesMappings = List(
    "/pacts" -> classOf[PactsResource])

}