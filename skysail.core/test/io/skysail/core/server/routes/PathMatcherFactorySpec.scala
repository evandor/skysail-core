//package io.skysail.core.server.routes
//
//import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.server.{PathMatcher, _}
//import akka.http.scaladsl.testkit.ScalatestRouteTest
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//import org.scalatest.{Matchers, WordSpec, _}
//import org.slf4j.LoggerFactory
//
//@RunWith(classOf[JUnitRunner])
//class PathMatcherFactorySpec extends WordSpec with BeforeAndAfterEach with Matchers with ScalatestRouteTest {
//
//  private val log = LoggerFactory.getLogger(this.getClass)
//
//  "The PathMatcherFactory" should {
//
//    "create a pathMatcher matching an 'empty path' definition for an app without version" in {
//      val testedRoute = testRouteForPath("appPath", "")
//      expectFailureFor(testedRoute, "/")
//      expectSuccessFor(testedRoute, "/appPath")
//    }
//
//    "create a pathMatcher matching an 'empty path' definition for an app with version" in {
//      val testedRoute = testRouteForPath("appPath/v1", "")
//      expectFailureFor(testedRoute, "/")
//      expectFailureFor(testedRoute, "/appPath")
//      expectSuccessFor(testedRoute, "/appPath/v1")
//    }
//
//    "leave GET requests to other paths unhandled for app without version" in {
//      val testedRoute = testRouteForPath("appPath", "")
//      expectSuccessFor(testedRoute, "/appPath")
//      expectFailureFor(testedRoute, "/kermit")
//    }
//
//    "leave GET requests to other paths unhandled for app with version" in {
//      val testedRoute = testRouteForPath("appPath/v1", "")
//      expectSuccessFor(testedRoute, "/appPath/v1")
//      expectFailureFor(testedRoute, "/kermit")
//    }
//
//    "create a pathMatcher matching a 'slash path' definition for an app without version" in {
//      val testedRoute = testRouteForPath("appPath", "/")
//      expectSuccessFor(testedRoute, "/appPath/")
//      expectFailureFor(testedRoute, "/appPath/more")
//    }
//
//    "create a pathMatcher matching a 'slash path' definition for an app with version" in {
//      val testedRoute = testRouteForPath("appPath/v1", "/")
//      expectFailureFor(testedRoute, "/appPath/")
//      expectFailureFor(testedRoute, "/appPath/v1")
//      expectSuccessFor(testedRoute, "/appPath/v1/")
//      expectFailureFor(testedRoute, "/appPath/more")
//    }
//
//    "create a pathMatcher matching a 'simple path' definition for an app without version" in {
//      val testedRoute = testRouteForPath("appPath", "/sub")
//      expectSuccessFor(testedRoute, "/appPath/sub")
//    }
//
//    "create a pathMatcher matching a 'simple path' definition for an app with version" in {
//      val testedRoute = testRouteForPath("appPath/v1", "/sub")
//      expectFailureFor(testedRoute, "/appPath/sub")
//      expectSuccessFor(testedRoute, "/appPath/v1/sub")
//    }
//
//    "create a pathMatcher matching a path definition with segments for an app without version" in {
//      val testedRoute = testRouteForPath("appPath", "/seg1/seg2")
//      expectSuccessFor(testedRoute, "/appPath/seg1/seg2")
//      expectFailureFor(testedRoute, "/appPath/seg1/seg2/")
//      expectFailureFor(testedRoute, "/appPath/seg1/seg2/test")
//    }
//
//    "create a pathMatcher matching a path definition with segments for an app with version" in {
//      val testedRoute = testRouteForPath("appPath/v1", "/seg1/seg2")
//      expectFailureFor(testedRoute, "/appPath/seg1/seg2")
//      expectSuccessFor(testedRoute, "/appPath/v1/seg1/seg2")
//      expectFailureFor(testedRoute, "/appPath/v1/seg1/seg2/")
//      expectFailureFor(testedRoute, "/appPath/v1/seg1/seg2/test")
//    }
//
//    "create a pathMatcher matching a path definition with placeholder for an app without version" in {
//      expectSuccessFor(testRouteForPath("appPath", "/seg1/:id"), "/appPath/seg1/76dbbd1c-6242")
//      expectSuccessFor(testRouteForPath("appPath", "/seg1/:id/"), "/appPath/seg1/76dbbd1c-6242/")
//      expectSuccessFor(testRouteForPath("appPath", "/seg1/:id/seg2"), "/appPath/seg1/76dbbd1c-6242/seg2")
//    }
//
//    "create a pathMatcher matching a path definition with placeholder for an app with version" in {
//      expectFailureFor(testRouteForPath("appPath/v1", "/seg1/:id"), "/appPath/seg1/76dbbd1c-6242-498f-a064-55a2f6ad3d41")
//
//      expectSuccessFor(testRouteForPath("appPath/v1", "/seg1/:id"), "/appPath/v1/seg1/76dbbd1c")
//      expectSuccessFor(testRouteForPath("appPath/v1", "/seg1/:id/"), "/appPath/v1/seg1/76dbbd1c/")
//    }
//
//    "create a pathMatcher matching a 'catchAll path' definition" in {
//      val testedRoute = testRouteForPath("appPath", "/catchAll/*")
//      expectFailureFor(testedRoute, "/appPath/catch")
//      expectSuccessFor(testedRoute, "/appPath/catchAll")
//      expectSuccessFor(testedRoute, "/appPath/catchAll/")
//      expectSuccessFor(testedRoute, "/appPath/catchAll/test")
//      expectSuccessFor(testedRoute, "/appPath/catchAll/test/")
//      expectSuccessFor(testedRoute, "/appPath/catchAll/test/sub")
//      expectSuccessFor(testedRoute, "/appPath/catchAll/test/sub/")
//    }
//
//  }
//
//  "The PathMatcherFactory also" should {
//
//    "create a pathMatcher matching a path containing a parameter" in {
//      val testedRoute = testRouteForPath("appPath", "/contact/:id")
//      expectSuccessFor(testedRoute, "/appPath/contact/0")
//    }
//
//  }
//
//  private def expectSuccessFor(testedRoute: Route, testPath: String): Unit = {
//    log info s" - testing '$testPath' against route definition, expecting success"
//    Get(testPath) ~> testedRoute ~> check {
//      successfulCall(testPath)
//    }
//  }
//
//  private def expectFailureFor(testedRoute: Route, testPath: String): Unit = {
//    //log info s" - testing '${testPath}' against route definition, expecting failure"
//    Get(testPath) ~> testedRoute ~> check {
//      rejectedCall()
//    }
//  }
//
//  private def successfulCall(path: String) = {
//    responseAs[String] shouldEqual s"successfully matched" // '${path}'"
//  }
//
//  private def rejectedCall() = {
//    handled shouldBe false
//  }
//
//  private def testRouteForPath(appPath: String, path: String): Route = {
//    val m = if (appPath.contains("/")) {
//      //appPath.split("/").fold(PathMatchers.E)((a,b) => a / b).toString()
//      PathMatcherFactory.matcherFor("appPath" / "v1", path)
//    } else {
//      PathMatcherFactory.matcherFor(appPath, path)
//    }
//    m match {
//      case (pm: Any, Unit) => get {
//        pathPrefix(pm.asInstanceOf[PathMatcher[Unit]]) {
//          complete {
//            s"successfully matched"
//          }
//        }
//      }
//      case (pm: Any, e: Class[Tuple1[_]]) => get {
//        println(s"hier: ${pm.getClass.getName}")
//        if (pm.isInstanceOf[PathMatcher[Tuple1[List[String]]]]) {
//          pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[List[String]]]]) { i =>
//            complete {
//              log info s"matched(1) ${i}"
//              s"successfully matched"
//            }
//          }
//        } else {
//          pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[String]]]) { i =>
//            complete {
//              log info s"matched(1) ${i}"
//              s"successfully matched"
//            }
//          }
//        }
//
//      }
//      case (pm: Any, e: Tuple2[_, _]) => get {
//        pathPrefix(pm.asInstanceOf[PathMatcher[Tuple1[String]]]) { i =>
//          complete {
//            log info s"matched(2) ${i}"
//            s"successfully matched"
//          }
//        }
//      }
//      case (first: Any, second: Any) => println(s"Unmatched! First '$first', Second ${second}");
//        get {
//          pathPrefix("") {
//            complete {
//              "error"
//            }
//          }
//        }
//    }
//  }
//
//}