package io.skysail.restlet.utils

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import io.skysail.core.restlet.SkysailServerResource
import org.hamcrest.CoreMatchers._
import org.junit.Assert._
import io.skysail.core.restlet.resources.ListServerResource
import io.skysail.core.model.TestEntity
import io.skysail.core.restlet.resources.EntityServerResource
import io.skysail.core.restlet.utils.ScalaReflectionUtils

class ReflectionUtilsSpec extends FlatSpec {

  
//  "the parameterized type of String" should "be ???" in {
//    val parameterizedType = ScalaReflectionUtils.getParameterizedType(classOf[String])
//    assertThat(parameterizedType.getName, is("java.lang.String"))
//  }

  "The parameterized type of EntityServerResource[TestEntity]" should "be 'TestEntity'" in {
    val parameterizedType = ScalaReflectionUtils.getParameterizedType(classOf[EntityServerResource[TestEntity]])
    assertThat(parameterizedType.getName, is("io.skysail.restlet.router.TestEntity"))
  }

//  "An instance of SkysailServerResource[List[TestEntity]]" should "has the generic type of 'TestEntity'" in {
//    val parameterizedType = ScalaReflectionUtils.getParameterizedType(classOf[ListServerResource[List[TestEntity]]])
//    assertThat(parameterizedType.getName, is("io.skysail.restlet.router.TestEntity"))
//  }

}