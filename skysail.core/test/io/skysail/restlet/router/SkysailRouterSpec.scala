package io.skysail.restlet.router

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import io.skysail.restlet.SkysailServerResource
import org.hamcrest.CoreMatchers._
import org.junit.Assert._
import io.skysail.restlet.resources.ListServerResource

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