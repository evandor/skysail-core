package io.skysail.app.wyt;

import akka.http.scaladsl.server.PathMatcher
import io.skysail.app.wyt.WytApplication._
import io.skysail.core.app.{ApplicationProvider, _}
import org.osgi.service.component.annotations._

object WytApplication {
  val APPLICATION_NAME = "wyt"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=wyt") }, service = Array(classOf[ApplicationProvider]))
class WytApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Wait-your-turn backend") with ApplicationProvider {

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("root") / "app"
    List(
      RouteMapping("/pacts",root / "pacts", classOf[PactsResource]))
  }

}