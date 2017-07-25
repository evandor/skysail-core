package io.skysail.core.app

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import org.mockito.Mockito

class SkysailApplicationSpec extends FlatSpec with BeforeAndAfterEach {

  "An ApiVersion" should "not accept versions lower than zero" in {
    assertThrows[IllegalArgumentException] {
      ApiVersion(-1)
    }
  }

  "An ApiVersion" should "not accept version zero" in {
    assertThrows[IllegalArgumentException] {
      ApiVersion(0)
    }
  }

  "An ApiVersion" should "prefix its version with 'v' when calling toString" in {
    val version = ApiVersion(10)
    assert(version.toString == "v10")
  }

}
