package io.skysail.core.server.routes

import java.lang.annotation.Annotation
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.pattern.ask
import akka.util.Timeout
import io.skysail.api.security.AuthenticationService
import io.skysail.core.Constants
import io.skysail.core.akka.ResponseEvent
import io.skysail.core.app.{ApplicationProvider, RouteMapping, SkysailApplication}
import io.skysail.core.resources.Resource
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.actors.{ApplicationActor, BundleActor, BundlesActor}
import io.skysail.core.server.directives.MyDirectives._
import io.skysail.core.server.routes.RoutesCreator._
import org.osgi.framework.wiring.BundleCapability
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag
import scala.util.{Failure, Success}

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

  implicit val timeout: Timeout = 3.seconds

  val capabilitiesFuture = (SkysailApplication.getBundlesActor(system) ? BundlesActor.GetCapabilities()).mapTo[Map[Long, List[BundleCapability]]]
  val capabilities = Await.result(capabilitiesFuture, 1.seconds)

  val bundleIdsWithClientCapabilities = capabilities.filter {
    entry => entry._2.filter { cap => Constants.CLIENT_CAPABILITY.equals(cap.getNamespace) }.size > 0
  }.map { m => m._1 }

  val clientClFuture = (SkysailApplication.getBundleActor(system, bundleIdsWithClientCapabilities.head) ? BundleActor.GetClassloader()).mapTo[ClassLoader]
  val clientClassloader = Await.result(clientClFuture, 1.seconds)

  val pathMatcherFactory = PathMatcherFactory

  def createRoute(mapping: RouteMapping[_], appInfoProvider: ApplicationProvider): Route = {
    val appRoute = appInfoProvider.appModel.appRoute
    log info s"creating route from [${appInfoProvider.appModel.appPath()}]${mapping.path} -> ${mapping.resourceClass.getSimpleName}[${mapping.getEntityType()}]"
    val pathMatcher = PathMatcherFactory.matcherFor(appRoute, mapping.path.trim())
    val appSelector = getApplicationActorSelection(system, appInfoProvider.getClass.getName)
    staticResources() ~ matcher(pathMatcher, mapping, appInfoProvider) ~ clientPath() ~ indexPath()
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
        getFromResourceDirectory("client", getClientClassloader /*classOf[AkkaServer].getClassLoader*/)
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader /*classOf[AkkaServer].getClassLoader*/)
      }
    }
  }

  private def staticResources(): Route = {
    path("static") {
      get {
        // & redirectToTrailingSlashIfMissing(TemporaryRedirect)) {
        implicit val classloader = clientClassloader /*classOf[AkkaServer].getClassLoader*/
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

  private def matcher(pathMatcherWithClass: (PathMatcher[_], Any), mapping: RouteMapping[_], appProvider: ApplicationProvider): Route = {

    val getAnnotation = requestAnnotationForGet(mapping.resourceClass)

//    pathPrefix(pathMatcherWithClass._1.asInstanceOf[PathMatcher[Unit]]) {
//      test() {
//        authenticationDirective(authentication) { username =>
//          get {
//            extractRequestContext {
//              ctx =>
//                test1("test1str") { f =>
//                  println(f)
//                  routeWithUnmatchedPath(ctx, mapping, appProvider)
//                }
//            }
//          } ~
//            post {
//              extractRequestContext {
//                ctx =>
//                  routeWithUnmatchedPath(ctx, mapping, appProvider)
//              }
//            }
//        }
//      }
//    }

    pathMatcherWithClass match {
      case (pm: Any, Unit) =>
        pathPrefix(pm.asInstanceOf[PathMatcher[Unit]]) {
          test() {
            authenticationDirective(authentication) { username =>
              get {
                extractRequestContext {
                  ctx =>
                    test1("test1str") { f =>
                      println(f)
                      routeWithUnmatchedPath(ctx, mapping, appProvider)
                    }
                }
              } ~
                post {
                  extractRequestContext {
                    ctx =>
                      routeWithUnmatchedPath(ctx, mapping, appProvider)
                  }
                }
            }
          }
        }
      case (pm: Any, e: Class[Tuple1[_]]) => get {
        pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[List[String]]]]) { urlParameter =>
          test() {
            authenticationDirective(authentication) { username =>
              get {
                extractRequestContext {
                  ctx =>
                    test1("test1str") { f =>
                      println(f)
                      routeWithUnmatchedPath(ctx, mapping, appProvider, urlParameter)
                    }
                }
              } ~
                post {
                  extractRequestContext {
                    ctx =>
                      routeWithUnmatchedPath(ctx, mapping, appProvider, urlParameter)
                  }
                }
            }
          }
        }
      }
      case (first: Any, second: Any) =>
        println(s"Unmatched! First '$first', Second ${second}"); get {
        pathPrefix("") {
          complete {
            "error"
          }
        }
      }
    }

  }

  private def createRoute(mapping: RouteMapping[_], appProvider: ApplicationProvider, urlParameter: List[String] = List()): Route = {
    test() {
      authenticationDirective(authentication) { username =>
        get {
          extractRequestContext {
            ctx =>
              test1("test1str") { f =>
                routeWithUnmatchedPath(ctx, mapping, appProvider, urlParameter)
              }
          }
        } ~
          post {
            extractRequestContext {
              ctx =>
                routeWithUnmatchedPath(ctx, mapping, appProvider, urlParameter)
            }
          }
      }
    }
  }

  private def routeWithUnmatchedPath(ctx: RequestContext, mapping: RouteMapping[_], appProvider: ApplicationProvider, urlParameter: List[String] = List()): Route = {
    extractUnmatchedPath { unmatchedPath =>
      val applicationActor = getApplicationActorSelection(system, appProvider.getClass.getName)
      val processCommand = ApplicationActor.ProcessCommand(ctx, mapping.resourceClass, urlParameter, unmatchedPath)
      //println(new PrivateMethodExposer(system)('printTree)())
      val t = (applicationActor ? processCommand).mapTo[ResponseEvent[_]]
      onComplete(t) {
        case Success(result) => complete(result.httpResponse)
        case Failure(failure) => log error s"Failure>>> ${failure}"; complete(StatusCodes.BadRequest, failure)
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

  private def getClientClassloader() = clientClassloader

}