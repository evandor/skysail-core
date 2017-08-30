package io.skysail.app.demo

import io.skysail.app.demo.DemoApplication._
import io.skysail.core.app._
import org.osgi.service.cm.ConfigurationAdmin
import org.osgi.service.component.annotations._

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=demo") }, service = Array(classOf[ApplicationProvider]))
class DemoApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail Demo Application") with ApplicationProvider {

  @Reference
  var configAdmin: ConfigurationAdmin = null

  override def routesMappings = List(
    "" -> classOf[EsController],
    "/" -> classOf[ContactsController],
    "/indices" -> classOf[IndicesController],
    "/indices/" -> classOf[IndicesController],
    "/configs" -> classOf[ConfigsController],
    "/mappings" -> classOf[MappingController],
    "/assets" -> classOf[MyAssetsController],
    "/allassets/*" -> classOf[MyAssetsController2],
    "/contacts" -> classOf[ContactsController])

  def getConfigs() = configAdmin.listConfigurations(null).map(x => ConfigDetails(x)).toList
  //def getConfig(pid: String): ConfigDetails = new ConfigDetails(configAdmin.getConfiguration(pid))
}