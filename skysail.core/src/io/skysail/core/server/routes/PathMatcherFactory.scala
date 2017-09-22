package io.skysail.core.server.routes

import io.skysail.core.app.RouteMapping
import akka.http.scaladsl.server.Route
import org.slf4j.LoggerFactory
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive1, PathMatcher, RequestContext, Route }
import akka.http.scaladsl.server.directives.{ AuthenticationDirective, Credentials }
import scala.util.matching.Regex
import scala.util.matching.Regex.MatchIterator
import akka.http.javadsl.server.PathMatcher1

object PathMatcherFactory {

  private val log = LoggerFactory.getLogger(this.getClass)

  def matcherFor(appRoute: /*PathMatcher[Unit]*/String, path: String): (PathMatcher[_], Any) = {
    log info s"creating pathMatcher for '${path}'"

    path.trim() match {
      case "" => (appRoute ~ PathEnd,Unit)
      case "/" => (appRoute / PathEnd,Unit)
      case p if p.endsWith("/*") => (handleCatchAll(appRoute, p),Unit)
      case p if (containsParameters(p)) => handleParameters(appRoute, p)
      case p if (containsSegments(p)) => (handleSegments(appRoute, p),Unit)
      case any => (appRoute / getMatcher(any) ~ PathEnd,Unit)
    }
  }

  private def handleCatchAll(appRoute: akka.http.scaladsl.server.PathMatcher[Unit], p: String) = {
    appRoute / PathMatcher(p.substring(1, p.length() - 2))
  }

  private def handleSegments(appRoute: akka.http.scaladsl.server.PathMatcher[Unit], p: String) = {
    val segments = p.split("/").toList.filter(seg => seg != null && seg.trim() != "")
    segments.foldLeft(appRoute)((a, b) => a / b) ~ PathEnd
  }

  private def handleParameters(appRoute: PathMatcher[Unit], p: String):(PathMatcher[_],Any) = {
    println("hier: ':(.)*$'") 
    val pattern = new Regex(":(.)*$")
    println("applying " + p)
    println ((pattern findAllIn p).mkString(","))
    PathEnd
    val segments = p.split("/").toList.filter(seg => seg != null && seg.trim() != "")
    println(segments)
    val r = segments.foldLeft(appRoute)((a, b) => substituteIfPattern[Unit](a, b, pattern findAllIn p)) ~ PathEnd
    
    if (segments.size == 2) {
      val s = appRoute / PathMatcher(segments(0)) / IntNumber
      println("S:" + s)
      (s, classOf[Tuple1[Int]])    
    } else {
      (r,Unit)
    }
    
  }
  
  private def substituteIfPattern[L](a: PathMatcher[L], b: String, mi: MatchIterator):PathMatcher[L] = {
    a / b 
//    a match {
//      case _:PathMatcher[Unit] => if (b == ":id") a / IntNumber else a / b
//      case _:PathMatcher[_] =>  a / b
//    }
  }

  private def getMatcher(path: String) = {
    val trimmed = path.trim();
    if (trimmed.startsWith("/")) PathMatcher(trimmed.substring(1)) else PathMatcher(trimmed)
  }

  private def containsSegments(p: String) = p.substring(1, p.length() - 2).contains("/")
  
  private def containsParameters(p: String) = p.contains(":")

}