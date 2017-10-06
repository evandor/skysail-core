package $basePackageName$

import $basePackageName$.TemplateApplication._
import org.osgi.service.cm.ConfigurationAdmin
import org.osgi.service.component.annotations._
import io.skysail.core.app.menus.MenuItem
import io.skysail.core.app._
import org.osgi.service.component.ComponentContext

object TemplateApplication {
  val APPLICATION_NAME = "template"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=template") }, service = Array(classOf[ApplicationProvider]))
class TemplateApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail Template Application") with ApplicationProvider {
  
  override def routesMappings = List(
    RouteMapping("", classOf[AppsResource])
  )

}