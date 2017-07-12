package io.skysail.core.app

import akka.actor.{ Actor, ActorSystem, Props, ActorLogging }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.{ PathMatcher, Route }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, StatusCodes }
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import io.skysail.core.security.config.SecurityConfigBuilder
import io.skysail.core.restlet.RouteBuilder
import io.skysail.core.app.resources.DefaultResource
import io.skysail.core.app.resources.LoginResource
import io.skysail.core.restlet.services.ResourceBundleProvider
import io.skysail.core.model.ApplicationModel
import io.skysail.core.restlet.resources.GetBids
import io.skysail.core.restlet.resources.Bid
import io.skysail.core.app.resources.DefaultResource2
import io.skysail.core.restlet.resources.Bids
import io.skysail.core.restlet.resources.Auction
import java.util.Dictionary
import org.osgi.service.component._
import org.osgi.service.component.annotations._
import org.osgi.service.cm.ManagedService
import spray.json.DefaultJsonProtocol._
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.io.StdIn
import io.skysail.core.akka.ResourceDefinition
import io.skysail.core.model.ApplicationModel2
import io.skysail.core.model.ApplicationModel2
import io.skysail.core.app.resources.DefaultResource3
import java.util.concurrent.atomic.AtomicInteger
import io.skysail.core.app.resources.AkkaRedirectResource
import akka.http.scaladsl.model.HttpResponse

object SkysailRootApplication {
  val ROOT_APPLICATION_NAME = "root"

  val LOGIN_PATH = "/_login"
  val LOGIN_CALLBACK = "/_logincallback"
  val PROFILE_PATH = "/_profile"
  val PUPLIC_PATH = "/_public"
  val LOGOUT_PATH = "/_logout"

  val CONFIG_IDENTIFIER_LANDINGPAGE_NOT_AUTHENTICATED = "landingPage.notAuthenticated"
  val CONFIG_IDENTIFIER_LANDINGPAGE_AUTHENTICATED = "landingPage.authenticated"

}

@Component(
  immediate = true,
  property = { Array("service.pid=landingpages") },
  service = Array(classOf[ApplicationProvider], classOf[ApplicationRoutesProvider], classOf[ResourceBundleProvider], classOf[ManagedService]))
class SkysailRootApplication extends SkysailApplication(SkysailRootApplication.ROOT_APPLICATION_NAME, null)
    with ApplicationProvider
    with ApplicationRoutesProvider
    with ResourceBundleProvider
    with ManagedService {

  var properties: Dictionary[String, _] = null
  var appRoutes: List[Route] = _
  var system: ActorSystem = _
  val cnt = new AtomicInteger(0)

  @Activate
  override def activate(componentContext: ComponentContext) = {
    log info s"activating ${this.getClass.getName}"
    if (getContext() != null) {
      setContext(getContext().createChildContext())
    }
    this.componentContext = componentContext
  }

  @Deactivate
  override def deactivate(componentContext: ComponentContext) = {
    log info s"deactivating ${this.getClass.getName}"
    this.componentContext = null
  }

  def updated(props: Dictionary[String, _]): Unit = this.properties = props

  override def routes(): List[Route] = {
    var akkaModel = ApplicationModel2(SkysailRootApplication.ROOT_APPLICATION_NAME, null)
    akkaModel.addResourceModel("first", classOf[DefaultResource2])
    akkaModel.addResourceModel("second", classOf[DefaultResource3])
    akkaModel.addResourceModel("third", classOf[AkkaRedirectResource])
    val pathResourceTuple = akkaModel.getResourceModels().map {
      m =>
        {
          log info s"creating route for ${akkaModel.name}/${m.pathMatcher}"
          (m.pathMatcher, m.targetResourceClass)
        }
    }
    pathResourceTuple.map { prt => createRoute2(prt._1, prt._2) }.toList
  }

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.OPTIONAL)
  def setApplicationListProvider(service: ScalaServiceListProvider) = SkysailApplication.setServiceListProvider(service)

  def unsetApplicationListProvider(service: ScalaServiceListProvider) = SkysailApplication.unsetServiceListProvider(service)

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
  def setActorSystem(as: ActorSystem) = this.system = as
  def unsetActorSystem(as: ActorSystem) = this.system = null

  override def defineSecurityConfig(securityConfigBuilder: SecurityConfigBuilder) = {
    securityConfigBuilder.authorizeRequests().startsWithMatcher("").permitAll()
  }

  override def attach() {
    router.attach(new RouteBuilder("/", classOf[DefaultResource]))
    router.attach(new RouteBuilder(SkysailRootApplication.LOGIN_PATH, classOf[LoginResource]))
    //    router.attach(new RouteBuilder(SkysailRootApplication.LOGOUT_PATH, classOf[LogoutResource]))
    //    router.attach(new RouteBuilder(SkysailRootApplication.PROFILE_PATH, classOf[ProfileResource]))
    //    router.attach(new RouteBuilder("/logs", classOf[LogsResource]))
  }

  def getRedirectTo(defaultResource: DefaultResource): String = {
    if (properties == null) {
      return null
    }
    if (!isAuthenticated(defaultResource.getRequest())) {
      val lPnotAuth = properties.get(SkysailRootApplication.CONFIG_IDENTIFIER_LANDINGPAGE_NOT_AUTHENTICATED)
      return if (lPnotAuth != null) lPnotAuth.toString() else null
    }
    val landingPage = properties.get(SkysailRootApplication.CONFIG_IDENTIFIER_LANDINGPAGE_AUTHENTICATED).toString()
    if (landingPage == null || "".equals(landingPage) || "/".equals(landingPage)) {
      return null
    }
    return landingPage
  }

  private def createRoute2(appPath: PathMatcher[Unit], cls: Class[_ <: ResourceDefinition[_]]) = {
//    val routeRootActor = system.actorOf(Props.apply(cls), cls.getSimpleName + "-" + cnt.incrementAndGet())
    path(appPath) {
      get { ctx =>
        {
          implicit val timeout: Timeout = 5.seconds
          implicit val system = ActorSystem()
          implicit val executionContext = system.dispatcher
          implicit val materializer = ActorMaterializer()

          //implicit val bidFormat = jsonFormat2(Bid)
          //implicit val bidsFormat = jsonFormat1(Bids)
          val routeRootActor = system.actorOf(Props.apply(cls), cls.getSimpleName + "-" + cnt.incrementAndGet())

          val bids = (routeRootActor ? ctx).mapTo[HttpResponse]
          
          ctx.complete(bids)
        }
      }
    }
  }

}