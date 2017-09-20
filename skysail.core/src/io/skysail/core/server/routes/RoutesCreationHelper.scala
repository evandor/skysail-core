package io.skysail.core.server.routes

import io.skysail.core.app.RouteMapping
import akka.http.scaladsl.server.Route
import org.slf4j.LoggerFactory
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive1, PathMatcher, RequestContext, Route }
import akka.http.scaladsl.server.directives.{ AuthenticationDirective, Credentials }

class RoutesCreationHelper {

  private val log = LoggerFactory.getLogger(this.getClass)

  def createRoute(path: String, appRoute: PathMatcher[Unit]): PathMatcher[Unit] = {
    log info s"creating route from ${path} -> ..."

    path.trim() match {
      case "" =>
        appRoute ~ PathEnd
//      case "/" =>
//        appRoute / PathEnd
//      case p if (p.endsWith("/*")) =>
//        appRoute / PathMatcher(p.substring(1, p.length() - 2))
//      case p if (p.substring(1, p.length() - 2).contains("/")) =>
//        val segments = p.split("/").toList.filter(seg => seg != null && seg.trim() != "")
//        segments.foldLeft(appRoute)((a, b) => a / b) ~ PathEnd
      case any => appRoute / getMatcher(any) ~ PathEnd
    }
  }

  private def getMatcher(path: String) = {
    val trimmed = path.trim();
    if (trimmed.startsWith("/")) PathMatcher(trimmed.substring(1)) else PathMatcher(trimmed)
  }

}