package io.skysail.core.server.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import org.slf4j.LoggerFactory

import scala.util.matching.Regex
import scala.util.matching.Regex.MatchIterator

object PathMatcherFactory {

  private val log = LoggerFactory.getLogger(this.getClass)

  private val pattern = new Regex(":(.)*$")

  def matcherFor(appRoute: PathMatcher[Unit], path: String): (PathMatcher[_], Any) = {

    path.trim() match {
      case "" => (appRoute ~ PathEnd, Unit)
      case "/" => (appRoute / PathEnd, Unit)
      case p if p.endsWith("/*") => (handleCatchAll(appRoute, p), Unit)
      case p if (containsParameters(p)) => handleParameters(appRoute, p)
      case p if (containsSegments(p)) => (handleSegments(appRoute, p), Unit)
      case any => (appRoute / getMatcher(any) ~ PathEnd, Unit)
    }
  }

  private def handleCatchAll(appRoute: akka.http.scaladsl.server.PathMatcher[Unit], p: String) = {
    appRoute / PathMatcher(p.substring(1, p.length() - 2))
  }

  private def handleParameters(appRoute: PathMatcher[Unit], p: String): (PathMatcher[_], Any) = {
    val segments = splitBySlashes(p)

    val t = PathMatcher("seg1") / PathMatchers.Segment / PathMatcher("seg2") ~ PathEnd

    val segDescriptors = SegmentDescriptor("appPath") :: segments.map(SegmentDescriptor(_))
    //val t2 = segDescriptors.reduce((a,b) => a.pathMatcher() / b.pathMatcher())// ~ PathEnd

    var res: scala.collection.mutable.ListBuffer[PathMatcher[_]] = scala.collection.mutable.ListBuffer()
    res += PathMatcher("appPath")
    implicit val join = akka.http.scaladsl.server.util.TupleOps.Join
    segments.foreach(seg => {
      val x = if (seg.trim.startsWith(":"))
        (res.reverse.head.asInstanceOf[PathMatcher[Unit]] / PathMatchers.Segment).asInstanceOf[PathMatcher[Tuple1[List[String]]]]
      else
        res.reverse.head / PathMatcher(seg)
      res += x
    })
    res += res.reverse.head ~ PathEnd

    if (segments.size >= 2) {
      val s = appRoute / PathMatcher(segments(0)) / PathMatchers.Segments(1)
      //(res.reverse.head, classOf[Tuple1[List[String]]])
      (s, classOf[Tuple1[String]])
    } else {
      val r = segments.foldLeft(appRoute)((a, b) => a / b) ~ PathEnd
      (r, Unit)
    }
  }

  private def handleSegments(appRoute: akka.http.scaladsl.server.PathMatcher[Unit], p: String) = {
    splitBySlashes(p).foldLeft(appRoute)((a, b) => a / b) ~ PathEnd
  }

  private def substituteIfPattern[L](a: PathMatcher[L], b: String, mi: MatchIterator): PathMatcher[L] = {
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

  private def splitBySlashes(p: String) = {
    p.split("/").toList.filter(seg => seg != null && seg.trim() != "")
  }


  case class SegmentDescriptor(seg: String) {
    //    this(pathMatcher: PathMatcher[_]) {
    //
    //    }
    def pathMatcher(): PathMatcher[_] = {
      if (seg.trim.startsWith(":")) PathMatchers.Segment else PathMatcher(seg)
    }
  }

}