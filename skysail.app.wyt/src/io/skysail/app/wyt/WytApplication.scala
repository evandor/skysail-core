package io.skysail.app.wyt;

import org.osgi.service.component._
import org.osgi.service.component.annotations._

import akka.actor.ActorSystem
import io.skysail.app.wyt.WytApplication._
import io.skysail.core.app._
import io.skysail.core.ApiVersion

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

}