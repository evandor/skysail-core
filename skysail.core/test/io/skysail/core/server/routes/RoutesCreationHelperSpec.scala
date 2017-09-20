package io.skysail.core.server.routes
import akka.http.javadsl.testkit.JUnitRouteTest

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import io.skysail.core.app.ApiVersion
import io.skysail.core.app.RouteMapping

import scala.reflect.runtime.universe._
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.model.Uri.Path
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._

@RunWith(classOf[JUnitRunner])
class RoutesCreationHelperSpec extends WordSpec with BeforeAndAfterEach with Matchers with ScalatestRouteTest {

//  "Given an empty path, createRoute" should "return an 'appRoute ~ PathEnd' matcher" in {
//    //assertThrows[IllegalArgumentException] { new ApplicationModel(null,ApiVersion(1),"desc") }
//    val helper = new RoutesCreationHelper()
//    val appRoute = PathMatcher("app")
//    //assert(helper.createRoute("",appRoute). != null)
//  }

  val smallRoute =
    get {
      pathSingleSlash {
        complete {
          "Captain on the bridge!"
        }
      } ~
        path("ping") {
          complete("PONG!")
        }
    }

  "The service" should {

    "return a greeting for GET requests to the root path" in {
      // tests:
      Get() ~> smallRoute ~> check {
        responseAs[String] shouldEqual "Captain on the bridge!"
      }
    }

    "return a 'PONG!' response for GET requests to /ping" in {
      // tests:
      Get("/ping") ~> smallRoute ~> check {
        responseAs[String] shouldEqual "PONG!"
      }
    }

    "leave GET requests to other paths unhandled" in {
      // tests:
      Get("/kermit") ~> smallRoute ~> check {
        handled shouldBe false
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      // tests:
      Put() ~> Route.seal(smallRoute) ~> check {
        status shouldEqual StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}