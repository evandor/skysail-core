package io.skysail.core.model

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.junit._
import org.assertj.core.api.Assertions._


@RunWith(classOf[JUnitRunner])
class EntityModelSpec extends FlatSpec with BeforeAndAfterEach {

  var entityModel: EntityModel = null

  override def beforeEach() {
    entityModel = EntityModel(new TestEntityController().getType())
  }

  "An EntityModel" should "not accept a null entity in constructor" in {
    assertThrows[IllegalArgumentException] { new EntityModel(null) }
  }
  
   "An EntityModel" should "have the entities name as its model name" in {
    assert(entityModel.name == "io.skysail.core.model.TestEntity")
  }

  "An EntityModel" should "identify the @Field-Annotated fields of the provided entity class" in {
//    println(entityModel.members)
    println(entityModel.fields)
    entityModel.fields.foreach( println(_))
  }

}