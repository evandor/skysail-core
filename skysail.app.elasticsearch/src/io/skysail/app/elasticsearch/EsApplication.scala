package io.skysail.app.elasticsearch

import io.skysail.core.app.{ApiVersion, ApplicationProvider, RouteMapping, SkysailApplication}
import org.osgi.service.component.annotations._
import io.skysail.app.elasticsearch.EsApplication._

object EsApplication {
  val APPLICATION_NAME = "es"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=demo") }, service = Array(classOf[ApplicationProvider]))
class EsApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Elasticsearch Application") with ApplicationProvider {

  override def routesMappings = List(
    RouteMapping("",classOf[IndicesResource]),
    RouteMapping("/indices",classOf[IndicesResource]),
    RouteMapping("/indices/",classOf[IndicesResource])
  )

}
