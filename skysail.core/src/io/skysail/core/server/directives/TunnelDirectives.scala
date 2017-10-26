package io.skysail.core.server.directives

import akka.http.scaladsl.model.{HttpMethod, HttpMethods, HttpRequest}
import akka.http.scaladsl.server._
import org.slf4j.LoggerFactory

trait TunnelDirectives {

  private val log = LoggerFactory.getLogger(this.getClass)

  def handleOptionalTunnelMethod(tunnelParam: Option[String]): Directive0 = {
    val f: HttpRequest => HttpRequest =
      if (tunnelParam.isDefined) {
        tunnelParam.get.trim().toUpperCase() match {
          case "PUT" => tunnelTo(HttpMethods.PUT)
          case "DELETE" => tunnelTo(HttpMethods.DELETE)
          case _ => log info s"hier with ${tunnelParam}"; req: HttpRequest => req
        }
      } else {
        req => req
      }
    mapRequestContext(_ mapRequest f)
  }

  private def tunnelTo(newMethod: HttpMethod) = {
    log info s"tunneling method: -> ${newMethod}"
    req: HttpRequest => req.copy(method = newMethod)
  }

  private def mapRequestContext(f: RequestContext ⇒ RequestContext): Directive0 = {
    mapInnerRoute { inner ⇒ ctx ⇒ inner(f(ctx)) }
  }

  private def mapInnerRoute(f: Route ⇒ Route): Directive0 =
    Directive { inner ⇒ f(inner(())) }
}

object TunnelDirectives extends TunnelDirectives {}
