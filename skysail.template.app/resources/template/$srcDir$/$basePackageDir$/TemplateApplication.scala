package $basePackageName$

import org.osgi.service.component._
import org.osgi.service.component.annotations._

import akka.actor.ActorSystem
import $basePackageName$.TemplateApplication._
import io.skysail.core.app._

object TemplateApplication {
  val APPLICATION_NAME = "demo"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=demo") }, service = Array(classOf[ApplicationRoutesProvider]))
class TemplateApplication extends BackendApplication(APPLICATION_NAME, API_VERSION) with ApplicationRoutesProvider {

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
  def setActorSystem(as: ActorSystem) = this.system = as
  def unsetActorSystem(as: ActorSystem) = this.system = null
  
  override def routesMappings = List(
    "apps" -> classOf[AppsResource])

}