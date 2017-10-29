package io.skysail.core.model

import akka.http.scaladsl.server.PathMatcher

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import io.skysail.core.app.ApiVersion
import io.skysail.core.app.RouteMapping

import scala.reflect.runtime.universe._

@RunWith(classOf[JUnitRunner])
class ApplicationModelSpec extends FlatSpec {

  "An ApplicationModel" should "not accept a null value as its name" in {
    assertThrows[IllegalArgumentException] { new ApplicationModel(null,ApiVersion(1),"desc") }
  }

  "An ApplicationModel" should "not accept an empty value as its name" in {
    assertThrows[IllegalArgumentException] { new ApplicationModel("",ApiVersion(1),"desc") }
    assertThrows[IllegalArgumentException] { new ApplicationModel(" ",ApiVersion(1),"desc") }
  }

  "An empty ApplicationModel" should "be created successfully for a given name" in {
    val model = new ApplicationModel("appName",ApiVersion(1),"desc")
    assert(model != null)
    assert(model.name == "appName")
    assert(model.description == "desc")
  }

//  "An ApplicationModel" should "create links without API version if no apiVersion is provided" in {
//    val model = ApplicationModel("appName",null, "desc")
//    model.addControllerModel("/list", classOf[TestEntitiesResource])
//    model.addControllerModel("/list/", classOf[PostTestEntityResource])
////    val links = model.linksFor(classOf[TestEntitiesResource])//.filter { l => l. }
////    assert(links.size == 1)
////    assert(links.head.getUri == "/appName/list/")
//  }

 /* "An ApplicationModel" should "return the parameterized class of the ControllerModel when added" in {
    val appModel = new ApplicationModel("appName",null, "desc")

    val cls = appModel.addResourceModel(RouteMapping("/path", classOf[TestStringEntityController]))

    assert(cls.isDefined)
    assert(cls.get == typeOf[String])
  }*/
  
  /*"An ApplicationModel" should "retrieve an already added controllerModel by its class name" in {
    val appModel = new ApplicationModel("appName",null, "desc")
    appModel.addResourceModel(RouteMapping("/path", classOf[TestStringEntityController]))
    
    val resourceModel = appModel.controllerModelFor(classOf[TestStringEntityController])

    assert(resourceModel.isDefined)
    //assert(resourceModel.get.appModel == appModel)
    assert(resourceModel.get.entityClass == typeOf[String])
    //assert(resourceModel.get.resourceClass == classOf[TestStringEntityController])
  }*/

  "An ApplicationModel" should "add an ControllerModel (identified by its path) only once" in {
    val model = new  ApplicationModel("appName",null,"desc")
    val root: PathMatcher[Unit] = PathMatcher("appName")
    val entityClass1 = model.addResourceModel(RouteMapping("/path", root / "path", classOf[TestStringEntityController]))
    val entityClass2 = model.addResourceModel(RouteMapping("/path", root / "path", classOf[TestStringEntityController]))
    
    assert(entityClass1.isDefined)
    assert(entityClass2.isEmpty)
  } 
  
//  "Given a SkysailServerResource, an ApplicatioModel" should "provide access to the resource's entityModel" in {
//    val model = ApplicationModel("appName",new ApiVersion(1),List())
//    model.addResourceModel("/list", classOf[TestEntitiesResource])
//    val entityModel = model.entityModelFor(new TestEntitiesResource())    
//    assert(entityModel.isDefined)
//    assert(entityModel.get.entityClass == classOf[TestEntity])
//  }
//
//  "An ApplicationModel" should "provide the LinkModel for a resource identified by its class" in {
//    val model = ApplicationModel("appName",new ApiVersion(1),List())
//    model.addResourceModel("/list", classOf[TestEntitiesResource])
//    model.addResourceModel("/list/", classOf[PostTestEntityResource])
//   // model.addResourceModel("/list/{id}", classOf[TestEntityResource])
//    //println(model)
//    val links = model.linksFor(classOf[TestEntitiesResource])
//    assert(links.size == 1)
//    assert(links.head.getUri == "/appName/v1/list/")
//  }
//  
//  "An ApplicationModel" should "provide the link to the update resource for an entity of the list resource" in {
//    val model = ApplicationModel("appName",new ApiVersion(1),List())
//    model.addResourceModel("/list", classOf[TestEntitiesResource])
//    model.addResourceModel("/list/", classOf[PostTestEntityResource])
//    //model.addResourceModel("/list/{id}", classOf[TestEntityResource])
//    val links = model.linksFor(classOf[TestEntitiesResource])
//    assert(links.size == 1)
//    assert(links.head.getUri == "/appName/v1/list/")
//  }
//
//  "An ApplicationModel" should "provide a decent toString representation" in {
//    val model = ApplicationModel("appName",null,List())
//    model.addResourceModel("/list", classOf[TestEntitiesResource])
//    model.addResourceModel("/list/{id}", classOf[TestEntityResource])
//    println(model)
//  }

}
