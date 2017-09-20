package io.skysail.core.server.routes
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

@RunWith(classOf[JUnitRunner])
class RoutesCreationHelperSpec extends FlatSpec with BeforeAndAfterEach {

  
  "Given an empty path, createRoute" should "return an 'appRoute ~ PathEnd' matcher" in {
    //assertThrows[IllegalArgumentException] { new ApplicationModel(null,ApiVersion(1),"desc") }
    val helper = new RoutesCreationHelper()
    val appRoute = PathMatcher("app")
    assert(helper.createRoute("",appRoute). != null)
  }

}