package skysail.app.bm

import skysail.app.bm.TemplateApplication._
import org.osgi.service.cm.ConfigurationAdmin
import org.osgi.service.component.annotations._
import io.skysail.core.app.menus.MenuItem
import io.skysail.core.app._
import org.osgi.service.component.ComponentContext

object TemplateApplication {
  val APPLICATION_NAME = "demo"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=demo") }, service = Array(classOf[ApplicationProvider]))
class DemoApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail Demo Application") with ApplicationProvider {

  override def routesMappings = List(
    //"apps" -> classOf[AppsResource]
    )

}