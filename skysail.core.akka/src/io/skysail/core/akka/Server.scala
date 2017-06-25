package io.skysail.core.app

import org.osgi.service.component.annotations._
import scala.collection.JavaConverters._
import org.slf4j.LoggerFactory
import java.text.DecimalFormat
import scala.collection.mutable.ListBuffer
import org.osgi.service.cm.ManagedService
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http


@Component(immediate = true)
class Server {

	val log = LoggerFactory.getLogger(this.getClass)
  val routes = scala.collection.mutable.ListBuffer[Route]()
  //var bindingFuture: 
  
  //@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MULTIPLE)
  def addApplicationRoutes(provider: ApplicationRoutesProvider): Unit = {
    routes ++= provider.routes
    //log.info("(+ ApplicationModel) (#{}) with name '{}'", ApplicationList.formatSize(applications), application.getName(): Any);
  }

  def removeApplicationRoutes(provider: ApplicationRoutesProvider): Unit = {
    routes --= provider.routes
    //log.info("(- ApplicationModel) name '{}', count is {} now", application.getName(), ApplicationList.formatSize(applications): Any);
  }

  @Activate
  def activate() {
    //Http().bindAndHandle(routes, "localhost", 8080)
  }

  @Deactivate
  def deactivate() {
    //val bindingFuture = Http().bindAndHandle(route ~ route2, "localhost", 8080)
  }

}
