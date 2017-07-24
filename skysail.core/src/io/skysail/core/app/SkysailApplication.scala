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
import io.skysail.api.text.Translation
import io.skysail.api.um.AuthenticationMode
import io.skysail.api.um.AuthenticationService
import io.skysail.core.ApiVersion
import io.skysail.core.domain.repo.ScalaDbRepository
import io.skysail.core.model.ResourceAssociationType
import io.skysail.core.restlet.menu.MenuItem
import io.skysail.core.restlet.services.ResourceBundleProvider
import io.skysail.core.restlet.utils.CompositeClassLoader
import io.skysail.core.restlet.utils.ScalaReflectionUtils
import io.skysail.core.akka.ResourceActor
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.dispatch.OnFailure

object SkysailApplication {
//  var serviceListProvider: ScalaServiceListProvider = null
//  def setServiceListProvider(service: ScalaServiceListProvider) = this.serviceListProvider = service
//  def unsetServiceListProvider(service: ScalaServiceListProvider) = this.serviceListProvider = null
}

abstract class SkysailApplication(
  name: String,
  val apiVersion: ApiVersion)
    //extends org.restlet.Application
    //with ApplicationProvider
    extends ApplicationRoutesProvider
    with ResourceBundleProvider {

  val IN_MEMORY_TRANSLATION_STORE = "InMemoryTranslationStore"

  val log = LoggerFactory.getLogger(classOf[SkysailApplication])

  //val associatedResourceClasses = scala.collection.mutable.ListBuffer[Tuple2[ResourceAssociationType, Class[_ <: SkysailServerResource[_]]]]()

  var componentContext: ComponentContext = null
  def getComponentContext() = componentContext

//  var applicationModel: ApplicationModel = null
//  def getApplicationModel() = applicationModel

  val repositories = new ArrayList[ScalaDbRepository]();

  var host = "localhost"
  def getHost = host

  var appRoutes: List[Route] = _
  var system: ActorSystem = _
  val cnt = new AtomicInteger(0)

  val stringContextMap = new java.util.HashMap[ApplicationContextId, String]()

  //setName(name);
  //getEncoderService().getIgnoredMediaTypes().add(SkysailApplication.SKYSAIL_SERVER_SENT_EVENTS);
  //getEncoderService().setEnabled(true);
  log.debug("Instanciating new Skysail ApplicationModel '{}'", this.getClass().getSimpleName());
  //applicationModel = ApplicationModel(name, apiVersion, associatedResourceClasses.toList)

  def this(name: String) = this(name, new ApiVersion(1))

  def routes(): List[Route] = List()

  def getResourceBundles() = List[ResourceBundle]()

  def getTemplatePaths[T](x$1: Class[T]): java.util.List[String] = {
    Collections.emptyList()
  }

  @Activate
  def activate(componentContext: ComponentContext) = {
    //        log.debug("Activating ApplicationModel {}", this.getClass().getName());
    this.componentContext = componentContext;
    //getApplicationModel().setBundleContext(getBundleContext());
  }

  @Activate
  def activate(appConfig: ApplicationConfiguration, componentContext: ComponentContext): Unit = {
    activate(componentContext);
    host = appConfig.host();
  }

  @Deactivate
  def deactivate(componentContext: ComponentContext): Unit = {
    this.componentContext = null;
  }

  def attach(): Unit = {
  }


  def getBundleContext(): Option[BundleContext] = {
    if (componentContext != null) {
      return Some(componentContext.getBundleContext())
    }
    None
  }

  def addRepository(repository: ScalaDbRepository) = {
    this.repositories.add(repository);
  }

//  def getMenuEntries(): List[MenuItem] = {
//    val appMenu = new MenuItem(getName(), applicationModel.appPath())
//    //appMenu.setCategory(APPLICATION_MAIN_MENU);
//    // appMenu.setIcon(stringContextMap.get(ApplicationContextId.IMG));
//    List(appMenu);
//  }

  def getSkysailApplication() = this

//  def defineSecurityConfig(securityConfigBuilder: SecurityConfigBuilder): Unit = {
//    securityConfigBuilder.authorizeRequests().startsWithMatcher("").authenticated();
//  }

//  def getAuthenticationService(): AuthenticationService = SkysailApplication.serviceListProvider.getAuthenticationService();
//  def getMetricsCollector() = SkysailApplication.serviceListProvider.getMetricsCollector()
//  def getSkysailApplicationService() = SkysailApplication.serviceListProvider.getSkysailApplicationService()

  def getBundle(): Bundle = {
    if (componentContext == null) {
      return null;
    }
    return componentContext.getBundleContext().getBundle();
  }

  //Class<? extends Entity>
  def getRepository[T <: ScalaDbRepository](entityClass: Class[_]): T = {
    val repo = repositories.asScala.filter { r =>
      val entityType = ScalaReflectionUtils.getParameterizedType(r.getClass())
      entityClass.isAssignableFrom(entityType)
    }.headOption
      .getOrElse(
        //log.warn("no matching repository found for '{}'", entityClass.getName())
        //return new NoOpDbRepository[T]()
        throw new RuntimeException("no repo"))
    repo.asInstanceOf[T]
  }

  def isNotInMemoryStore(translation: Translation) = IN_MEMORY_TRANSLATION_STORE != translation.getStoreName()

  //public <T extends ServerResource> List<RouteBuilder> getRouteBuilders(Class<T> cls) {
//  def getRouteBuilders(cls: Class[_]): List[RouteBuilder] = {
//    if (router == null) {
//      return List[RouteBuilder]()
//    }
//    return router.getRouteBuildersForResource(cls);
//  }
//
//  def routesMap: Map[String, RouteBuilder] = if (router == null) Map() else router.routesMap
//
//  def getRouteBuildersForResource(cls: Class[_] /*Class<? extends ServerResource>*/ ): List[RouteBuilder] = {
//    router.getRouteBuildersForResource(cls);
//  }

//  protected def addAssociatedResourceClasses(typeAndClassTuples: List[Tuple2[ResourceAssociationType, Class[_ <: SkysailServerResource[_]]]]): Unit = {
//    typeAndClassTuples.foreach(tupel => {
//      associatedResourceClasses += tupel
//    })
//  }

  //private def attachModel() = router.attach(new RouteBuilder("/_model", classOf[ModelResource]));

  protected def createRoute(appPath: PathMatcher[Unit], cls: Class[_ <: ResourceActor[_]]) = {
    path(appPath) {
      get { ctx =>
        {
          implicit val timeout: Timeout = 5.seconds
          implicit val system = ActorSystem()
          implicit val executionContext = system.dispatcher
          implicit val materializer = ActorMaterializer()

          val routeRootActor = system.actorOf(Props.apply(cls), cls.getSimpleName + "-" + cnt.incrementAndGet())

          ctx.complete {
            val bids = (routeRootActor ? ctx).mapTo[HttpResponse]
            println("root: " + routeRootActor)
            println("bids: " + bids)
            println("bids: " + bids.getClass.getName)
            bids
            //HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>")
          }
        }
      }
    }
  }

  protected def createRoute2(appPath: PathMatcher[Unit], cls: Class[_ <: ResourceActor[_]]) = {
    path(appPath) {
      get {
        extractRequestContext {
          ctx =>
            {
              implicit val askTimeout: Timeout = 3.seconds
              val actor = system.actorOf(Props.apply(cls), cls.getSimpleName + "-" + cnt.incrementAndGet())
              onSuccess((actor ? ctx).mapTo[HttpResponse]) { result => complete(result) }
            }
        }
      }
    }
  }

}