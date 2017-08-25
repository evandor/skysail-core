package io.skysail.core.app

import java.util.ArrayList
import java.util.Collections
import java.util.ResourceBundle
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.concurrent.duration.DurationInt
import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext
import org.osgi.service.component.ComponentContext
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Deactivate
import org.slf4j.LoggerFactory
import akka.actor.ActorSystem
import akka.actor.Props
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives.get
import akka.http.scaladsl.server.Directives.path
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import io.skysail.core.ScalaReflectionUtils
import io.skysail.core.akka.ResourceController
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import io.skysail.core.model.ApplicationModel
import akka.actor.ActorRef
import akka.actor.Status.{ Failure, Success }
import io.skysail.core.akka.PrivateMethodExposer
import io.skysail.core.server.{ ApplicationsActor, BundlesActor }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.util.Random
import scala.concurrent.Future
import scala.concurrent.Await
import io.skysail.core.app.SkysailApplication._
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import io.skysail.core.server.BundleActor
import akka.actor.ActorSelection

object SkysailApplication {
  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  case class InitResourceActorChain(val requestContext: RequestContext, val cls: Class[_ <: ResourceController[_]])
  case class CreateApplicationActor(val cls: Class[_ <: SkysailApplication], val appModel: ApplicationModel, bundleContext: Option[BundleContext])
  case class DeleteApplicationActor(val cls: Class[_ <: SkysailApplication])

  def getApplicationsActor(system: ActorSystem): ActorRef = {
    val applicationsActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName
    log debug s"searching applicationsActor @ path '${applicationsActorPath}' in system ${system}"
    val applicationsActorSelection = system.actorSelection(applicationsActorPath)
    val r = applicationsActorSelection.resolveOne(2.seconds)
    Await.result(r, 1.seconds)
  }

  def getApplicationActorSelection(system: ActorSystem, name: String): ActorSelection = {
    val applicationActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName + "/" + name
    system.actorSelection(applicationActorPath)
  }

  def getBundlesActor(system: ActorSystem): ActorSelection = {
    system.actorSelection("/user/" + classOf[BundlesActor].getSimpleName)
  }

  def getBundleActor(system: ActorSystem, bundleId: Long): ActorSelection = {
    //println(new PrivateMethodExposer(theSystem)('printTree)())
    val actorSelection = "/user/" + classOf[BundlesActor].getSimpleName + "/" + bundleId.toString
    println("searching for actorSelection " + actorSelection)
    system.actorSelection(actorSelection)
  }

}

abstract class SkysailApplication(name: String, val apiVersion: ApiVersion, description: String) extends ApplicationInfoProvider {

  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  def this(name: String, description: String) = this(name, new ApiVersion(1), description)

  val appModel = ApplicationModel(name, apiVersion, description)

  def routesMappings: List[(String, Class[_ <: ResourceController[_]])]

  var actorRefsMap = Map.empty[String, ActorRef]

  val routes = {
    routesMappings.foreach(m => {
      appModel.addResourceModel(m._1, m._2)
    })

    appModel.getResourceModels().map {
      m => (m.pathMatcher, m.targetResourceClass)
    }
  }

  //val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: SkysailServerResource[_]]]]()

  var componentContext: ComponentContext = null
  def getComponentContext() = componentContext

  var host = "localhost"
  def getHost = host

  var appRoutes: List[Route] = _
  var system: ActorSystem = _
  val cnt = new AtomicInteger(0)

  val stringContextMap = new java.util.HashMap[ApplicationContextId, String]()

  def getResourceBundles() = List[ResourceBundle]()

  def getTemplatePaths[T](x$1: Class[T]): java.util.List[String] = {
    Collections.emptyList()
  }

  @Activate
  def activate(componentContext: ComponentContext) = {
    log info s"activating ${this.getClass.getName}"
    this.componentContext = componentContext;
  }

  @Activate
  def activate(appConfig: ApplicationConfiguration, componentContext: ComponentContext): Unit = {
    activate(componentContext);
    host = appConfig.host();
  }

  @Deactivate
  def deactivate(componentContext: ComponentContext): Unit = {
    log info s"deactivating ${this.getClass.getName}"
    this.componentContext = null;
  }

  def getBundleContext(): Option[BundleContext] = {
    if (componentContext != null) {
      return Some(componentContext.getBundleContext())
    }
    None
  }

  def getSkysailApplication() = this

  def getBundle(): Bundle = {
    if (componentContext == null) {
      return null;
    }
    return componentContext.getBundleContext().getBundle();
  }

  private def getResourceActor(cls: Class[_ <: ResourceController[_]]) = actorRefsMap get cls.getName getOrElse {
    log info s"creating new actor for ${cls.getName}"
    val c = system.actorOf(Props.apply(cls), cls.getName)
    actorRefsMap += cls.getName -> c
    //system watch c
    c
  }

}

