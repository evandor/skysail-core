package io.skysail.core.server.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import io.skysail.core.app.{ApplicationProvider, SkysailApplication}
import io.skysail.core.app.SkysailApplication.DeleteApplicationActor
import org.slf4j.LoggerFactory

class RoutesTracker(system: ActorSystem, authentication: String) {

  private val log = LoggerFactory.getLogger(this.getClass())
  
  log info s"created RoutesTracker with ${authentication} authentication."

  private var routesBuffer = scala.collection.mutable.ListBuffer[Route]()
  
  private val routesCreator = RoutesCreator(system, authentication)

  def routes(): List[Route] = routesBuffer.toList

  def addRoutesFor(appInfoProvider: ApplicationProvider) = {
    log info "========================================="
    log info s"Adding routes from ${appInfoProvider.getClass.getName}"
    log info "========================================="
    val routesFromProvider = appInfoProvider.routes()
    routesBuffer ++= routesFromProvider.map { prt => routesCreator.createRoute(prt._1, prt._2, appInfoProvider) }.toList
  }

  def removeRoutesFrom(appInfoProvider: ApplicationProvider) = {
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