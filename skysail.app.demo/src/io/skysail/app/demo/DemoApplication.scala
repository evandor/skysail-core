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
import io.skysail.core.model.ApplicationModel2
import io.skysail.core.ApiVersion
import io.skysail.app.demo.DemoApplication._

object DemoApplication {
  val APPLICATION_NAME = "demo"
  val API_VERSION = ApiVersion(1)
}

@Component(
  immediate = true,
  property = { Array("service.pid=demo") },
  service = Array( classOf[ApplicationRoutesProvider]))
class DemoApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION)
    //with ApplicationProvider
    with ApplicationRoutesProvider {
  
  //var properties: Dictionary[String, _] = null

  @Activate
  override def activate(componentContext: ComponentContext) = {
    log info s"activating ${this.getClass.getName}"
  }

  @Deactivate
  override def deactivate(componentContext: ComponentContext) = {
    log info s"deactivating ${this.getClass.getName}"
  }

  //def updated(props: Dictionary[String, _]): Unit = this.properties = props

  override def routes(): List[Route] = {
    var akkaModel = ApplicationModel2(APPLICATION_NAME, API_VERSION)
    akkaModel.addResourceModel("apps", classOf[AppsResource])
    val pathResourceTuple = akkaModel.getResourceModels().map {
      m => (m.pathMatcher, m.targetResourceClass)
    }
    pathResourceTuple.map { prt => createRoute2(prt._1, prt._2) }.toList
  }

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
  def setActorSystem(as: ActorSystem) = this.system = as
  def unsetActorSystem(as: ActorSystem) = this.system = null

//  override def defineSecurityConfig(securityConfigBuilder: SecurityConfigBuilder) = {
//    securityConfigBuilder.authorizeRequests().startsWithMatcher("").permitAll()
//  }

 

}