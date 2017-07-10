package io.skysail.core.restlet.router

import org.scalatest._
import org.hamcrest.CoreMatchers._
import org.junit.Assert._

class SkysailRouterSpec extends FlatSpec {

  "An instance of SkysailServerResource[TestEntity]" should "has the generic type of 'TestEntity'" in {
    val parameterizedType = SkysailRouter.getResourcesGenericType(new TestEntityServerResource())
    assertThat(parameterizedType.getName, is("io.skysail.restlet.router.TestEntity"))
  }

  "An instance of SkysailServerResource[List[TestEntity]]" should "has the generic type of 'TestEntity'" in {
    val parameterizedType = SkysailRouter.getResourcesGenericType(new TestEntityListServerResource())
    assertThat(parameterizedType.getName, is("io.skysail.restlet.router.TestEntity"))
  }

}