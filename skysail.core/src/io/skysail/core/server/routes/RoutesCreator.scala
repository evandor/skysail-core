package io.skysail.core.server.routes

import java.lang.annotation.Annotation
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ ActorRef, ActorSelection, ActorSystem }
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive1, PathMatcher, RequestContext, Route }
import akka.http.scaladsl.server.directives.{ AuthenticationDirective, Credentials }
import akka.pattern.ask
import akka.util.Timeout
import com.fasterxml.jackson.databind.introspect.{ AnnotatedClass, JacksonAnnotationIntrospector }
import io.skysail.core.ScalaReflectionUtils
import io.skysail.core.akka.{ ResponseEvent }
import io.skysail.core.app.ApplicationProvider
import io.skysail.core.app.resources.BundlesResource
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.AkkaServer
import io.skysail.core.server.actors.ApplicationsActor
import io.skysail.core.server.directives.MyDirectives._
import io.skysail.core.server.routes.RoutesCreator._
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag
import akka.dispatch.OnFailure
import scala.util.{ Success, Failure }
import io.skysail.core.akka.PrivateMethodExposer
import io.skysail.core.Constants
import akka.http.scaladsl.model.StatusCodes
import io.skysail.core.resources.Resource
import io.skysail.core.app.SkysailApplication
import io.skysail.core.server.actors.BundlesActor
import akka.pattern.ask
import org.osgi.framework.wiring.BundleWiring
import org.osgi.framework.wiring.BundleCapability
import scala.concurrent.Await
import io.skysail.core.server.actors.BundleActor
import io.skysail.api.security.AuthenticationService
import io.skysail.core.app.RouteMapping

object RoutesCreator {

  def apply(system: ActorSystem): RoutesCreator = new RoutesCreator(system)

  def getApplicationActorSelection(system: ActorSystem, name: String): ActorSelection = {
    val applicationActorPath = "/user/" + Constants.APPLICATIONS_ACTOR_NAME + "/" + name
    system.actorSelection(applicationActorPath)
  }
}

class RoutesCreator(system: ActorSystem) {

  private val log = LoggerFactory.getLogger(this.getClass())
  
  log info s"instanciating new RoutesCreator"

  var authentication: AuthenticationService = null

  private val counter = new AtomicInteger(0)
  
  implicit val timeout: Timeout  = 1.seconds

  val capabilitiesFuture = (SkysailApplication.getBundlesActor(system) ? BundlesActor.GetCapabilities()).mapTo[Map[Long, List[BundleCapability]]]
  val capabilities = Await.result(capabilitiesFuture, 1.seconds)
  
  val bundleIdsWithClientCapabilities = capabilities.filter { 
    entry => entry._2.filter { cap => Constants.CLIENT_CAPABILITY.equals(cap.getNamespace) }.size > 0
  }.map { m => m._1 }
  
  val clientClFuture = (SkysailApplication.getBundleActor(system, bundleIdsWithClientCapabilities.head) ? BundleActor.GetClassloader()).mapTo[ClassLoader]
  val clientClassloader = Await.result(clientClFuture, 1.seconds)
  
    
  def createRoute(mapping: RouteMapping[_], appInfoProvider: ApplicationProvider): Route = {

    val appRoute = appInfoProvider.appModel.appRoute

    log info s"creating route from [${appInfoProvider.appModel.appPath()}]${mapping.path} -> ${mapping.resourceClass.getSimpleName}[${mapping.getEntityType()}]"

    val pathMatcher =
      mapping.path.trim() match {
        case "" => 
          appRoute ~ PathEnd
        case "/" => 
          appRoute / PathEnd
        case p if (p.endsWith("/*")) =>
          appRoute / PathMatcher(p.substring(1, p.length() - 2))
        case p if (p.substring(1,p.length()-2).contains("/")) =>
          val segments = p.split("/").toList.filter(seg => seg != null && seg.trim() != "")
          segments.foldLeft(appRoute)((a,b) => a / b) ~ PathEnd
        case any => appRoute / getMatcher(any) ~ PathEnd
      }

    val appSelector = getApplicationActorSelection(system, appInfoProvider.getClass.getName)

    staticResources() ~ matcher(pathMatcher, mapping.resourceClass, appInfoProvider.getClass.getName) ~ clientPath() ~ indexPath()
  }

  private def indexPath(): Route = {
    path("") {
      get {
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader)
      }
    }
  }

  private def clientPath(): Route = {
    pathPrefix("client") {
      get {
        getFromResourceDirectory("client", getClientClassloader/*classOf[AkkaServer].getClassLoader*/)
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader/*classOf[AkkaServer].getClassLoader*/)
      }
    }
  }

  private def staticResources(): Route = {
    path("static") {
      get {
        // & redirectToTrailingSlashIfMissing(TemporaryRedirect)) {
        implicit val classloader = clientClassloader/*classOf[AkkaServer].getClassLoader*/
        getFromResource("application.conf", ContentTypes.`application/json`, getClientClassloader)
      }
    } ~
      pathPrefix("client") {
        get {
          getFromResourceDirectory("client", getClientClassloader())
        }
      }
  }

  private def getMatcher(path: String) = {
    val trimmed = path.trim();
    if (trimmed.startsWith("/")) PathMatcher(trimmed.substring(1)) else PathMatcher(trimmed)
  }

  private def matcher(pathMatcher: PathMatcher[Unit], cls: Class[_ <: Resource[_]], name: String): Route = {

    val getAnnotation = requestAnnotationForGet(cls)

    pathPrefix(pathMatcher) {
      test() {
        authenticationDirective(authentication) { username =>
          get {
            extractRequestContext {
              ctx =>
                test1("test1str") { f =>
                  println(f)
                  routeWithUnmatchedPath(ctx, cls, name)
                }
            }
          }
        }
      }
    }
  }

  private def routeWithUnmatchedPath(ctx: RequestContext, cls: Class[_ <: Resource[_]], name: String): Route = {
    extractUnmatchedPath { unmatchedPath =>
      //implicit val askTimeout: Timeout = 2.seconds
      val applicationActor = getApplicationActorSelection(system, name)
      log debug s"executing route#${counter.incrementAndGet()}: ${applicationActor.pathString} ! Tuple3(ctx, cls, unmatchedPath):"

      //println(new PrivateMethodExposer(system)('printTree)())

      val t = (applicationActor ? Tuple3(ctx, cls, unmatchedPath)).mapTo[ResponseEvent[_]]
      onComplete(t) {
        case Success(result) => complete(result.httpResponse)
        case Failure(failure) => log error s"Failure ${failure}"; complete(StatusCodes.BadRequest, failure)
      }
    }
  }

  private def authenticationDirective(auth: AuthenticationService): Directive1[String] = auth.directive

  private def requestAnnotationForGet(cls: Class[_ <: Resource[_]]): Option[Annotation] = {
    try {
      val getMethod = cls.getMethod("get", classOf[ActorRef], classOf[ClassTag[_]])
      Some(getMethod.getAnnotation(classOf[AuthorizeByRole]))
    } catch {
      case e: Throwable => None //log.error(e.getMessage(), e)
    }
  }

  private def getClientClassloader() = /*classOf[AkkaServer].getClassLoader*/clientClassloader

}