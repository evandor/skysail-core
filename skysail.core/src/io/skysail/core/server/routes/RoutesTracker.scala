package io.skysail.core.server.routes

import scala.concurrent.duration.DurationInt
import org.slf4j.LoggerFactory
import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import io.skysail.core.app.ApplicationInfoProvider
import io.skysail.core.app.SkysailApplication
import io.skysail.core.app.SkysailApplication.CreateApplicationActor
import io.skysail.core.app.SkysailApplication.DeleteApplicationActor
import io.skysail.core.server.routes.RoutesCreator
import io.skysail.core.server.routes.RoutesCreator._

class RoutesTracker(system: ActorSystem, authentication: String) {

  private val log = LoggerFactory.getLogger(this.getClass())
  
  log info s"created RoutesTracker with ${authentication} authentication."

  private var routesBuffer = scala.collection.mutable.ListBuffer[Route]()
  
  private val routesCreator = RoutesCreator(system, authentication)

  def routes(): List[Route] = routesBuffer.toList

  def addRoutesFor(appInfoProvider: ApplicationInfoProvider) = {

    implicit val askTimeout: Timeout = 1.seconds
    val appsActor = SkysailApplication.getApplicationsActor(system)
    val appClass = appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]]
    val appModel = appInfoProvider.appModel()
    val optionalBundleContext = appInfoProvider.getBundleContext()

    appsActor ! CreateApplicationActor(appClass, appModel, optionalBundleContext)

    log info "========================================="
    log info s"Adding routes from ${appInfoProvider.getClass.getName}"
    log info "========================================="

    val routesFromProvider = appInfoProvider.routes()
    routesBuffer ++= routesFromProvider.map { prt => routesCreator.createRoute(prt._1, prt._2, appInfoProvider) }.toList
  }

  def removeRoutesFrom(appInfoProvider: ApplicationInfoProvider) = {
    val appsActor = SkysailApplication.getApplicationsActor(system)
    appsActor ! DeleteApplicationActor(appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]])

    log info "========================================="
    log info s"Removing routes from ${appInfoProvider.getClass.getName}"
    log info "========================================="
    //log info s"Removing routes ${s.routes()} not supplied no more from ${s.getClass.getName}"
    //routes --= s.routes()
    // TODO need to fix that, routes are not removed
    val routesFromProvider = appInfoProvider.routes()
    routesBuffer --= routesFromProvider.map { prt => routesCreator.createRoute(prt._1, prt._2, appInfoProvider) }.toList
  }


}