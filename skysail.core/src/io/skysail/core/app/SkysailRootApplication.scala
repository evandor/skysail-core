package io.skysail.core.app

import org.osgi.service.component.annotations._
import org.osgi.service.cm.ManagedService
import io.skysail.core.restlet.services.ResourceBundleProvider
import java.util.Dictionary

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import org.osgi.service.component._
import io.skysail.core.security.config.SecurityConfigBuilder
import io.skysail.core.restlet.RouteBuilder
import io.skysail.core.app.resources.DefaultResource
import io.skysail.core.app.resources.LoginResource
import akka.http.scaladsl.server.{ PathMatcher, Route }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, StatusCodes }
import io.skysail.core.model.ApplicationModel
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import io.skysail.core.restlet.resources.Bids
import io.skysail.core.restlet.resources.Auction
import akka.actor.Props
import akka.stream.ActorMaterializer
import akka.actor.{ Actor, ActorSystem, Props, ActorLogging }
import spray.json.DefaultJsonProtocol._
import scala.concurrent.duration._
import scala.io.StdIn
import io.skysail.core.restlet.resources.GetBids
import io.skysail.core.restlet.resources.Bid
import scala.concurrent.Future

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

  @Activate
  override def activate(componentContext: ComponentContext) = {
    if (getContext() != null) {
      setContext(getContext().createChildContext())
    }
    this.componentContext = componentContext
    //setComponentContext(componentContext)
    //dumpBundlesInformationToLog(componentContext.getBundleContext().getBundles())

    var akkaModel = ApplicationModel(SkysailRootApplication.ROOT_APPLICATION_NAME, apiVersion)
    akkaModel.addResourceModel("", classOf[DefaultResource])
    val pathResourceTuple = akkaModel.getResourceModels().map {
      m =>
        {
          log info s"creating route for ${akkaModel.name}/test${m.path}"
          (akkaModel.name / "test", m.targetResourceClass)
        }
    }
    appRoutes = pathResourceTuple.map { prt => createRoute(prt._1) }.toList
  }

  @Deactivate
  override def deactivate(componentContext: ComponentContext) = this.componentContext = null

  def updated(props: Dictionary[String, _]): Unit = this.properties = props

  override def routes(): List[Route] = {
    //val route = createRoute("auction" / "test")
    appRoutes
    //    List(route)
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

  private def createRoute(appPath: PathMatcher[Unit]) = {
    val auction2 = system.actorOf(Props[Auction], "auction2")
    path(appPath) {
      get {
        //complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        //complete((StatusCodes.Accepted, "get request for path: " + appPath))

        implicit val timeout: Timeout = 5.seconds
        implicit val system = ActorSystem()
        implicit val executionContext = system.dispatcher
        implicit val materializer = ActorMaterializer()
        implicit val bidFormat = jsonFormat2(Bid)
        implicit val bidsFormat = jsonFormat1(Bids)
        val bids: Future[Bids] = (auction2 ? GetBids).mapTo[Bids]
        complete(bids)
      } ~
        post {
          parameter("bid".as[Int], "user") { (bid, user) =>
            // place a bid, fire-and-forget
            auction2 ! Bid(user, bid)
            complete((StatusCodes.Accepted, "bid placed"))
          }
        }
    }
  }

}