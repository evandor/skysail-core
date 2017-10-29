package io.skysail.core.server.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import org.slf4j.LoggerFactory
//import shapeless._

import scala.util.matching.Regex
import scala.util.matching.Regex.MatchIterator

object PathMatcherFactory {

  private val log = LoggerFactory.getLogger(this.getClass)

  private val pattern = new Regex(":(.)*$")

//  object join2 extends Poly {
//    implicit def casePathMatcher[A, B](implicit t: akka.http.scaladsl.server.util.TupleOps.Join[A, B]) = use((a: PathMatcher[A], b: PathMatcher[B]) => a / b)
//  }

  def matcherFor(appRoute: PathMatcher[Unit], path: String): MyRoute = {
    path.trim() match {
      case "" => UnitRoute(appRoute ~ PathEnd)
      case "/" => UnitRoute(appRoute / PathEnd)
      case p if p.endsWith("/*") => UnitRoute(handleCatchAll(appRoute, p))
      case p if containsParameters(p) => handleParameters(appRoute, p)
      case p if containsSegments(p) => UnitRoute(handleSegments(appRoute, p))
      case any => UnitRoute(appRoute / getMatcher(any) ~ PathEnd)
    }
  }

  private def handleCatchAll(appRoute: akka.http.scaladsl.server.PathMatcher[Unit], p: String) = {
    appRoute / PathMatcher(p.substring(1, p.length() - 2))
  }

  private def handleParameters(appRoute: PathMatcher[Unit], p: String): MyRoute = {
    val segments = splitBySlashes(p)

    val splitted: Seq[String] = p.split("/").toList

    val matchers = splitted.map(createPathMatcher)

//    val r: HList = matchers.size match {
//      case 1 => matchers(0) :: HNil
//      case 2 => matchers(0) :: matchers(1) :: HNil
//      case 3 => matchers(0) :: matchers(1) :: matchers(2) :: HNil
//      case 4 => matchers(0) :: matchers(1) :: matchers(2) :: matchers(3) :: HNil
//      case 5 => matchers(0) :: matchers(1) :: matchers(2) :: matchers(3) :: matchers(4) :: HNil
//      case  _ => HNil//throw UnsupportedOperationException
//    }
//
//    //val resultingRoute = r.reduceLeft(join2)
//
//    val r2: PathMatcher[_] = matchers.size match {
//      case 1 => matchers(0)
//      case 2 => matchers(0) / matchers(1)
//      case 3 => matchers(0) / matchers(1) / matchers(2)
//      case 4 => matchers(0) / matchers(1) / matchers(2) / matchers(3)
//      case 5 => matchers(0) / matchers(1) / matchers(2) / matchers(3) / matchers(4)
//      case  _ => PathMatchers.Slash //throw UnsupportedOperationException
//    }




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
      val s = if (p.trim.endsWith("/")) {
        appRoute / PathMatcher(segments(0)) / PathMatchers.Segments(1) ~ PathMatchers.Slash
      } else {
        appRoute / PathMatcher(segments(0)) / PathMatchers.Segments(1)
      }
      //(res.reverse.head, classOf[Tuple1[List[String]]])
      StringRoute(s)//, classOf[Tuple1[String]])
    } else {
      val r = segments.foldLeft(appRoute)((a, b) => a / b) ~ PathEnd
      UnitRoute(r)
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

  private def createPathMatcher(str: String) = {
    log info s"creating pathMatcher for '${str}'"
    str match {
      case "" => PathMatchers.Neutral
      case _ if str.startsWith(":") => PathMatchers.Segment
      case _ => PathMatcher(str)
    }
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