package io.skysail.core.server.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, _}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec, _}
import org.slf4j.LoggerFactory

//@RunWith(classOf[JUnitRunner])
class ProgrammaticRouteDefinitionSpec extends WordSpec with BeforeAndAfterEach with Matchers with ScalatestRouteTest {

  private val log = LoggerFactory.getLogger(this.getClass)

  val route1: PathMatcher[Unit] = PathMatcher("app")
  val route2: PathMatcher1[String] = PathMatchers.Segment
  val route3: PathMatcher[Unit] = PathMatcher("segment")

//  override def beforeEach() {
//    route1 = PathMatcher("app")
//  }

  "The PathMatcherFactory" should {


    "create a pathMatcher matching a path definition with placeholder for an app without version" in {
      val v1 = route1 / route2
      val resultingRoute: PathMatcher[Tuple1[String]] = route1 / route2 / route3
    }

    "create a pathMatcher matching a path definition with placeholder for an app without version2" in {
      implicit val join = akka.http.scaladsl.server.util.TupleOps.Join
      val routeDef = List(route1, route2, route3)
     // val resultingRoute = routeDef.reduceLeftOption((a,b) => a / b)
//      val resultingRoute = routeDef.reduce((a,b) => {
//        a match {
//          case _: PathMatcher0 if(b.isInstanceOf[PathMatcher0]) => a / b.asInstanceOf[PathMatcher0]
//          case _: PathMatcher0 if(b.isInstanceOf[PathMatcher1[String]]) => a ~ PathMatchers.Slash ~[PathMatcher1[String]] b
//          case e: Any => a// println("EEE: " + e.toString); (a / b).asInstanceOf[PathMatcher1[Tuple1[String]]]
//        }
//      })
    }

  }


  private def expectSuccessFor(testedRoute: Route, testPath: String): Unit = {
    log info s" - testing '$testPath' against route definition, expecting success"
    Get(testPath) ~> testedRoute ~> check {
      successfulCall(testPath)
    }
  }

  private def expectFailureFor(testedRoute: Route, testPath: String): Unit = {
    //log info s" - testing '${testPath}' against route definition, expecting failure"
    Get(testPath) ~> testedRoute ~> check {
      rejectedCall()
    }
  }

  private def successfulCall(path: String) = {
    responseAs[String] shouldEqual s"successfully matched" // '${path}'"
  }

  private def rejectedCall() = {
    handled shouldBe false
  }

  private def testRouteForPath(appPath: String, path: String): Route = {
    val m = if (appPath.contains("/")) {
      //appPath.split("/").fold(PathMatchers.E)((a,b) => a / b).toString()
      PathMatcherFactory.matcherFor("appPath" / "v1", path)
    } else {
      PathMatcherFactory.matcherFor(appPath, path)
    }
    m match {
      case (pm: Any, Unit) => get {
        pathPrefix(pm.asInstanceOf[PathMatcher[Unit]]) {
          complete {
            s"successfully matched"
          }
        }
      }
      case (pm: Any, e: Class[Tuple1[_]]) => get {
        println(s"hier: ${pm.getClass.getName}")
        if (pm.isInstanceOf[PathMatcher[Tuple1[List[String]]]]) {
          pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[List[String]]]]) { i =>
            complete {
              log info s"matched(1) ${i}"
              s"successfully matched"
            }
          }
        } else {
          pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[String]]]) { i =>
            complete {
              log info s"matched(1) ${i}"
              s"successfully matched"
            }
          }
        }

      }
      case (pm: Any, e: Tuple2[_, _]) => get {
        pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[String]]]) { i =>
          complete {
            log info s"matched(2) ${i}"
            s"successfully matched"
          }
        }
      }
      case (first: Any, second: Any) => println(s"Unmatched! First '$first', Second ${second}");
        get {
          pathPrefix("") {
            complete {
              "error"
            }
          }
        }
    }
  }

}