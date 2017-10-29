package io.skysail.core.server.routes

import java.lang.annotation.Annotation
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ActorRef, ActorSelection, ActorSystem}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.ws.{BinaryMessage, Message, TextMessage}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Source}
import akka.util.Timeout
import io.skysail.api.security.AuthenticationService
import io.skysail.core.Constants
import io.skysail.core.app.{ApplicationProvider, RouteMapping, SkysailApplication}
import io.skysail.core.resources.Resource
import io.skysail.core.security.AuthorizeByRole
import io.skysail.core.server.actors.ApplicationActor.ProcessCommand
import io.skysail.core.server.actors.{BundleActor, BundlesActor}
import io.skysail.core.server.directives.MyDirectives._
import io.skysail.core.server.directives.TunnelDirectives._
import io.skysail.core.server.routes.RoutesCreator._
import org.osgi.framework.wiring.BundleCapability
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.reflect.ClassTag

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
  val clientClassloader = Await.result(clientClFuture, 3.seconds)

  val pathMatcherFactory = PathMatcherFactory


  def createRoute(mapping: RouteMapping[_], appInfoProvider: ApplicationProvider): Route = {
    val appRoute = appInfoProvider.appModel.appRoute
    log info s" >>> creating route from [${appInfoProvider.appModel.appPath()}]${mapping.path} -> ${mapping.resourceClass.getSimpleName}[${mapping.getEntityType()}]"
    val pathMatcherTypeTuple: MyRoute =
      if (mapping.pathMatcher != null) {
        mapping.classes.size match {
          case 0 => UnitRoute(mapping.pathMatcher)
          case 1 => StringRoute(mapping.pathMatcher)
          case _ => throw new UnsupportedOperationException
        }
      }
      else
        PathMatcherFactory.matcherFor(appRoute, mapping.path.trim())

    val appSelector = getApplicationActorSelection(system, appInfoProvider.getClass.getName)
    val route: Route = staticResources() ~ matcher(pathMatcherTypeTuple, mapping, appInfoProvider) ~ clientPath() ~ indexPath() ~ websocketPath()

    def myRejectionHandler =
      RejectionHandler.newBuilder()
        .handle { case MissingCookieRejection(cookieName) =>
          complete(HttpResponse(BadRequest, entity = "No cookies, no service!!!"))
        }
        .handle { case AuthorizationFailedRejection =>
          complete((Forbidden, "You're out of your depth!"))
        }
        .handle { case ValidationRejection(msg, _) =>
          complete((InternalServerError, "That wasn't valid! " + msg))
        }
        .handleAll[MethodRejection] { methodRejections =>
        val names = methodRejections.map(_.supported.name)
        complete((MethodNotAllowed, s"Can't do that! Supported: ${names mkString " or "}!"))
      }
        //        .handleNotFound {
        //          complete((NotFound, "Not here!"))
        //        }
        .result()

    handleRejections(myRejectionHandler) {
      route
    }
  }

  private def indexPath(): Route = {
    path("") {
      get {
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader)
      }
    } ~
      path("v2") {
        get {
          val r = io.skysail.core.app.resources.html.index4.apply()
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, r.body))
        }
      } ~
      path("c4") {
        parameterMap { map =>
          println("MAP: " + map)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "hier we are"))
        }
      }
  }

  def greeter: Flow[Message, Message, Any] =
    Flow[Message].mapConcat {
      case tm: TextMessage =>
        TextMessage(Source.single("Hello ") ++ tm.textStream ++ Source.single("!")) :: Nil
      case bm: BinaryMessage =>
        // ignore binary messages but drain content to avoid the stream being clogged
        // bm.dataStream.runWith(Sink.ignore)
        Nil
    }

  private def websocketPath(): Route = {
    path("websocket") {
      handleWebSocketMessages(greeter)
    }
  }

  private def clientPath(): Route = {
    pathPrefix("client") {
      get {
        getFromResourceDirectory("client", getClientClassloader)
        getFromResource("client/index.html", ContentTypes.`text/html(UTF-8)`, getClientClassloader)
      }
    }
  }

  private def staticResources(): Route = {
    pathPrefix("static") {
      get {
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

  private def matcher(pathMatcherWithClass: MyRoute, mapping: RouteMapping[_], appProvider: ApplicationProvider): Route = {

    val getAnnotation = requestAnnotationForGet(mapping.resourceClass)

    pathMatcherWithClass match {
      //case (pm: PathMatcher[Unit], Unit) =>
      case (pm: UnitRoute) =>
        pathPrefix(pm.pathMatcher.asInstanceOf[PathMatcher0]) {
          parameters('_method.?) { tunnelMethod =>
            authenticationDirective(authentication) { username =>
              handleOptionalTunnelMethod(tunnelMethod) {
                get {
                  extractRequestContext {
                    ctx => routeWithUnmatchedPath2(ctx, mapping, appProvider)
                  }
                } ~
                  post {
                    extractRequestContext {
                      ctx =>
                        routeWithUnmatchedPath2(ctx, mapping, appProvider)
                    }
                  }
              }
            }
          }
        }
      //case (pm: PathMatcher[Tuple1[String]], e: Class[Tuple1[_]]) => get {
      case (pm: StringRoute) =>
        pathPrefix(pm.pathMatcher.asInstanceOf[PathMatcher[Tuple1[String]]]) { urlParameter =>
          parameters('_method.?) { tunnelMethod =>
            authenticationDirective(authentication) { username =>
              optionalHeaderValueByName("Accept") { acceptHeader =>
                //handleOptionalTunnelMethod(tunnelMethod) {
                get {
                  log info s"getting..."
                  handleRequest(mapping, appProvider, List(urlParameter))
                } ~
                  post {
                    log info s"posting..."
                    handleRequest(mapping, appProvider, List(urlParameter))
                  } ~
                  put {
                    log info s"putting..."
                    handleRequest(mapping, appProvider, List(urlParameter))
                  }
                //}
              }
            }

          }
        }
      case m: Any =>
        (get | post | put | delete) {
          extractRequestContext { ctx =>
            complete {
              log info s"Ctx: ${ctx}"
              log info s"First: ${m}"
              "error1"
            }
          }
        }

    }

  }

  private def handleRequest(mapping: RouteMapping[_], appProvider: ApplicationProvider, urlParameter: List[String]) = {
    extractRequestContext {
      ctx => routeWithUnmatchedPath2(ctx, mapping, appProvider, urlParameter)
    }
  }

  private def createRoute(mapping: RouteMapping[_], appProvider: ApplicationProvider, urlParameter: List[String] = List()): Route

  = {
    test() {
      authenticationDirective(authentication) { username =>
        optionalHeaderValueByName("Accept") { acceptHeader =>
          get {
            extractRequestContext {
              ctx =>
                test1("test1str") { f =>
                  routeWithUnmatchedPath2(ctx, mapping, appProvider, urlParameter)
                }
            }
          } ~
            post {
              extractRequestContext {
                ctx =>
                  routeWithUnmatchedPath2(ctx, mapping, appProvider, urlParameter)
              }
            }
        }
      }
    }
  }

  private def routeWithUnmatchedPath2(
                                       ctx: RequestContext,
                                       mapping: RouteMapping[_],
                                       appProvider: ApplicationProvider,
                                       urlParameter: List[String] = List()): Route

  = {
    extractUnmatchedPath { unmatchedPath =>
      val applicationActor = getApplicationActorSelection(system, appProvider.getClass.getName)
      val clazz = mapping.resourceClass
      val resourceInstance = clazz.newInstance().asInstanceOf[Resource[_]]
      val processCommand = ProcessCommand(ctx, clazz, urlParameter, unmatchedPath)

      resourceInstance.createRoute(applicationActor, processCommand)(system)
    }
  }

  private def authenticationDirective(auth: AuthenticationService): Directive1[String]

  = auth.directive

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