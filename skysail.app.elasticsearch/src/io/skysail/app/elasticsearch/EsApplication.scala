package io.skysail.app.elasticsearch

import akka.http.scaladsl.server.{PathMatcher, PathMatchers}
import io.skysail.core.app.{ApiVersion, ApplicationProvider, RouteMapping, SkysailApplication}
import org.osgi.service.component.annotations._
import io.skysail.app.elasticsearch.EsApplication._
import io.skysail.core.app.menus.MenuItem

object EsApplication {
  val APPLICATION_NAME = "es"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=elasticsearch") }, service = Array(classOf[ApplicationProvider]))
class EsApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Elasticsearch Application") with ApplicationProvider {

  override def menu() = {
    Some(
      MenuItem("Elasticsearch", "fa-file-o", None, Some(List(
        MenuItem("Config", "fa-plus", Some("/demo/v1/configs")),
        MenuItem("Index", "fa-plus", Some("/es/v1/indices")),
        MenuItem("Indices II", "fa-plus", Some("/maincontent/generic/es/v1/indices"))
      ))))
  }

  override def routesMappings = {
    val root: PathMatcher[Unit] = PathMatcher("root")
    List(
      RouteMapping("",root, classOf[IndicesResource]),
      RouteMapping("/indices",root / "indices", classOf[IndicesResource]),
      RouteMapping("/indices/", root / PathMatcher("indices") ~ PathMatchers.Slash, classOf[IndicesResource])
    )
  }

}
