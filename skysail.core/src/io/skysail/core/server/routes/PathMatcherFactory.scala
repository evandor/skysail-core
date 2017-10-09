package io.skysail.core.server.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import org.slf4j.LoggerFactory

import scala.util.matching.Regex
import scala.util.matching.Regex.MatchIterator

object PathMatcherFactory {

  private val log = LoggerFactory.getLogger(this.getClass)

  def matcherFor(appRoute: PathMatcher[Unit], path: String): (PathMatcher[_], Any) = {

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
    val pattern = new Regex(":(.)*$")
    PathEnd
    val segments = p.split("/").toList.filter(seg => seg != null && seg.trim() != "")
    val r = segments.foldLeft(appRoute)((a, b) => substituteIfPattern[Unit](a, b, pattern findAllIn p)) ~ PathEnd
    
    if (segments.size == 2) {
      val s = appRoute / PathMatcher(segments(0)) / PathMatchers.Segments(1)
      //println("S:" + s.getClass.getName)
      (s, classOf[Tuple1[String]])
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