package io.skysail.core.server.routes

import java.lang.annotation.Annotation
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive1, PathMatcher, RequestContext, Route}
import akka.http.scaladsl.server.directives.{AuthenticationDirective, Credentials}
import akka.pattern.ask
import akka.util.Timeout
import com.fasterxml.jackson.databind.introspect.{AnnotatedClass, JacksonAnnotationIntrospector}
import io.skysail.core.ScalaReflectionUtils
import io.skysail.core.akka.{ResourceController, ResponseEvent}
import io.skysail.core.app.ApplicationProvider
import io.skysail.core.app.resources.BundlesController
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.AkkaServer
import io.skysail.core.server.actors.ApplicationsActor
import io.skysail.core.server.directives.MyDirectives._
import io.skysail.core.server.routes.RoutesCreator._
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag

object RoutesCreator {

  def apply(system: ActorSystem, authentication: String): RoutesCreator = new RoutesCreator(system, authentication)

  def getApplicationActorSelection(system: ActorSystem, name: String): ActorSelection = {
    val applicationActorPath = "/user/" + classOf[ApplicationsActor].getSimpleName + "/" + name
    system.actorSelection(applicationActorPath)
  }
}

class RoutesCreator(system: ActorSystem, authentication: String) {

  private val log = LoggerFactory.getLogger(this.getClass())

  private val counter = new AtomicInteger(0)

  def createRoute(appPath: String, cls: Class[_ <: ResourceController[_]], appInfoProvider: ApplicationProvider): Route = {

    val appRoute = appInfoProvider.appModel.appRoute

    log info s"creating route from [${appInfoProvider.appModel.appPath()}]${appPath}"

    val pathMatcher =
      appPath.trim() match {
        case "" => appRoute ~ PathEnd
        case "/" => appRoute / PathEnd
        case p if (p.endsWith("/*")) =>
          println("matching " + p.substring(1, p.length() - 2)); appRoute / PathMatcher(p.substring(1, p.length() - 2))
        case any => appRoute / getMatcher(any) ~ PathEnd
      }

    val appSelector = getApplicationActorSelection(system, appInfoProvider.getClass.getName)

    staticResources() ~ matcher2(pathMatcher, cls, appInfoProvider.getClass.getName) ~ clientPath() ~ indexPath()

  }

  private def indexPath(): Route = {
    path("") {
      get {
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, classOf[AkkaServer].getClassLoader)
      }
    }
  }

  private def clientPath(): Route = {
    pathPrefix("client") {
      get {
        getFromResourceDirectory("client", classOf[AkkaServer].getClassLoader)
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, classOf[AkkaServer].getClassLoader)
      }
    }
  }

  private def staticResources(): Route = {
    path("static") {
      get {
        // & redirectToTrailingSlashIfMissing(TemporaryRedirect)) {
        implicit val classloader = classOf[AkkaServer].getClassLoader
        getFromResource("application.conf", ContentTypes.`application/json`, classloader)
      }
    } ~
      pathPrefix("client") {
        get {
          val classloader = classOf[AkkaServer].getClassLoader
          //getFromDirectory("client")
          getFromResourceDirectory("client", classloader)
        }
      }
  }

  private def getMatcher(path: String) = {
    val trimmed = path.trim();
    if (trimmed.startsWith("/")) PathMatcher(trimmed.substring(1)) else PathMatcher(trimmed)
  }

  def myUserPassAuthenticator(credentials: Credentials): Option[String] =
    credentials match {
      case p@Credentials.Provided(id) if p.verify("p4ssw0rd") => Some(id)
      case _ => None
    }

  //  private def matcher(pathMatcher: PathMatcher[Unit], cls: Class[_ <: ResourceController[_]], name: String): Route = {
  //    pathPrefix(pathMatcher) {
  //      test() {
  //        authenticateBasic(realm = "secure site", myUserPassAuthenticator) { username =>
  //          get {
  //            extractRequestContext {
  //              ctx =>
  //                test1("") { f => {
  //                  extractUnmatchedPath { unmatchedPath =>
  //                    log debug s"executing route#${counter.incrementAndGet()}"
  //                    implicit val askTimeout: Timeout = 3.seconds
  //                    //println(new PrivateMethodExposer(theSystem)('printTree)())
  //                    val appActorSelection = getApplicationActorSelection(system, name)
  //                    val t = (appActorSelection ? (ctx, cls, unmatchedPath)).mapTo[ResponseEvent[_]]
  //                    onSuccess(t) { x => complete(x.httpResponse) }
  //                  }
  //                }
  //                }
  //            }
  //          }
  //        }
  //      }
  //    }
  //  }

  private def matcher2(pathMatcher: PathMatcher[Unit], cls: Class[_ <: ResourceController[_]], name: String): Route = {

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

  //  val orderGetOrPutWithMethod =
  //    path("order" / IntNumber) & (get | put) & extractMethod
  //
  //  val route =
  //    orderGetOrPutWithMethod { (id, m) =>
  //      complete(s"Received ${m.name} request for order $id")
  //    }

  private def routeWithUnmatchedPath(ctx: RequestContext, cls: Class[_ <: ResourceController[_]], name: String): Route = {
    extractUnmatchedPath { unmatchedPath =>
      log debug s"executing route#${counter.incrementAndGet()}"
      implicit val askTimeout: Timeout = 3.seconds
      //println(new PrivateMethodExposer(theSystem)('printTree)())
      val appActorSelection = getApplicationActorSelection(system, name)
      val t = (appActorSelection ? Tuple3(ctx, cls, unmatchedPath)).mapTo[ResponseEvent[_]]
      onSuccess(t) { x => complete(x.httpResponse) }
    }
  }

  private def authenticationDirective(auth: String): Directive1[String] = {
    auth match {
      case "HTTP_BASIC" => authenticateBasic(realm = "secure site", myUserPassAuthenticator)
      case _ => test1("hi")
    }
  }

  private def requestAnnotationForGet(cls: Class[_ <: ResourceController[_]]): Option[Annotation] = {
    try {
      val getMethod = cls.getMethod("get", classOf[ActorRef], classOf[ClassTag[_]])
      Some(getMethod.getAnnotation(classOf[AuthorizeByRole]))
    } catch {
      case e: Throwable => None //log.error(e.getMessage(), e)
    }
  }


}