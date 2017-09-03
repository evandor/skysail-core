package io.skysail.core.model

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ControllerModelSpec extends FlatSpec with BeforeAndAfterEach {

  "A ResourceModel" should "not accept a null value as its path" in {
    assertThrows[IllegalArgumentException] { new ControllerModel(null, null) }
  }

  "A ResourceModel" should "not accept a null value as its target class" in {
    assertThrows[IllegalArgumentException] { new ControllerModel("/path", null) }
  }

//  "A ResourceModel" should "provide its path variables" in {
//    val resourceModel = ControllerModel("/path/:id/subpath/:name", classOf[TestStringEntityController])
//    assert(resourceModel.pathVariables == List("id","name"))
//  }

  "A ResourceModel" should "provide access to the target resource" in {
    val model = ControllerModel("/path", classOf[TestStringEntityController])
    assert(model.controllerClass == classOf[TestStringEntityController])
  }

//  "A ResourceModel" should "provide access to the target entity" in {
//    val model = ControllerModel("/path", classOf[TestEntityListController])
//    assert(model.entityClass == classOf[TestEntity])
//  }
//  
//  "A ResourceModel" should "provide the resource type" in {
//    val model = ControllerModel(null, "/path", classOf[TestController])
//    assert(model.resourceType == UNSPECIFIED_RESOURCE)
//  }
  
//  "A ResourceModel of a ListResource" should "be associated with its related EntityResource" in {
//    val model = SkysailResourceModel2("/path", classOf[TestListResource])
//    assert(model.associatedResourcesFor(ENTITY_RESOURCE_FOR_LIST_RESOURCE).head.getName == "io.skysail.core.model.TestEntityResource")
//  }
//  
//  "A ResourceModel" should "be associcated with its resources' 'linkedResources'" in {
//    val linkedResources = SkysailResourceModel2("/path", classOf[TestListResource]).associatedResourcesFor(LINKED_RESOURCE)
//    assert(linkedResources.size == 1)    
//    assert(linkedResources.head.getName == "io.skysail.core.model.TestEntityResource")
//  }
  
//  "An ResourceModel" should "provide a decent toString representation" in {
//    val model = new ControllerModel(null, "/path1", classOf[TestEntitiesResource])
//    println(model)
//  }

}