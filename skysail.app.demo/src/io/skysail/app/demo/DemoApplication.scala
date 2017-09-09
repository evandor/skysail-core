package io.skysail.app.demo

import io.skysail.app.demo.DemoApplication._
import io.skysail.core.app._
import org.osgi.service.cm.ConfigurationAdmin
import org.osgi.service.component.annotations._
import io.skysail.core.app.menus.MenuItem

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=demo") }, service = Array(classOf[ApplicationProvider]))
class DemoApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail Demo Application") with ApplicationProvider {

  @Reference
  var configAdmin: ConfigurationAdmin = null

  override def menu() = {
    Some(
      MenuItem("DemoApp", "fa-file-o", None, Some(List(
        MenuItem("ElasticSearch", "fa-plus", Some("/client/demo/v1/indices")),
        MenuItem("Contacts", "fa-user", None, Some(List(
          MenuItem("add Contact", "fa-plus", Some("/client/demo/v1/contacts/new"))    
        )))  
      ))))
  }

  override def routesMappings = List(
    RouteMapping("",classOf[EsResource]),
    RouteMapping("/",classOf[ContactsResource]),
    RouteMapping("/indices",classOf[IndicesResource]),
    RouteMapping("/indices/",classOf[IndicesResource]),
    RouteMapping("/configs",classOf[ConfigsResource]),
    RouteMapping("/mappings",classOf[MappingResource]),
    RouteMapping("/assets",classOf[MyAssetsController]),
    RouteMapping("/allassets/*",classOf[MyAssetsController2]),
    RouteMapping("/contacts",classOf[ContactsResource]),
    RouteMapping("/contacts/new",  classOf[PostContactResource]) // TODO fix that!!! need trailing slash to work
  )

  def getConfigs() = configAdmin.listConfigurations(null).map(x => ConfigDetails(x)).toList
  //def getConfig(pid: String): ConfigDetails = new ConfigDetails(configAdmin.getConfiguration(pid))
}