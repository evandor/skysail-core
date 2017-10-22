package io.skysail.core.server.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import io.skysail.api.security.AuthenticationService
import io.skysail.core.app.SkysailApplication.DeleteApplicationActor
import io.skysail.core.app.{ApplicationProvider, SkysailApplication}
import org.slf4j.LoggerFactory

class RoutesTracker(system: ActorSystem) {

  private val log = LoggerFactory.getLogger(this.getClass)

  private var routesBuffer = scala.collection.mutable.ListBuffer[Route]()
  private var routesMap = scala.collection.mutable.Map[String,List[Route]]()
  private val routesCreator = RoutesCreator(system)

  def routes(): List[Route] = routesMap.values.flatMap(_.toList).toList

  def addRoutesFor(appInfoProvider: ApplicationProvider): Unit = {
    log info s" >>> >>> >>> Adding routes from ${appInfoProvider.getClass.getName}"
    val routesFromProvider = appInfoProvider.routes()
    val  valueList = routesFromProvider.map { prt => routesCreator.createRoute(prt, appInfoProvider) }.toList
    routesMap += appInfoProvider.appModel().id -> valueList
  }

  def removeRoutesFrom(appInfoProvider: ApplicationProvider): Unit = {
    val appsActor = SkysailApplication.getApplicationsActor(system)
    appsActor ! DeleteApplicationActor(appInfoProvider.getClass.asInstanceOf[Class[SkysailApplication]])
    log info s" <<< <<< <<< Removing routes from ${appInfoProvider.getClass.getName}"
    routesMap.remove(appInfoProvider.appModel().id)
  }

  def setAuthentication(a: AuthenticationService): Unit = routesCreator.authentication = a

}