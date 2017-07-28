package io.skysail.app.demo

import java.util.Dictionary
import org.osgi.service.component._
import org.osgi.service.component.annotations._
import org.osgi.service.cm.ManagedService
import spray.json.DefaultJsonProtocol._
import scala.concurrent.duration._
import scala.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Route
import akka.actor.ActorSystem
import io.skysail.core.app._
import io.skysail.app.demo.DemoApplication._
import io.skysail.core.model.ApplicationModel

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=demo") }, service = Array(classOf[ApplicationRoutesProvider]))
class DemoApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION) with ApplicationRoutesProvider {

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
  def setActorSystem(as: ActorSystem) = this.system = as
  def unsetActorSystem(as: ActorSystem) = this.system = null

  override def routesMappings = List(
    "apps" -> classOf[AppsResource])

}