package io.skysail.core.model

import io.skysail.core.akka.actors._

class TestEntitiesResource extends ListResource[String] {  
  def get() = List("hi", "content")
  //override def linkedResourceClasses() = List(classOf[PostTestEntityResource])
}

class TestResource extends EntityResource[String] {
  def get() = "test"
}

//class TestEntityResource extends EntityServerResource[TestEntity] { 
//  override def linkedResourceClasses() = List(classOf[PutTestEntityResource])
//
//  def getEntity(): TestEntity = {
//    TestEntity(Some("2"), "hi2", "content2")
//  }
//}
//
class PostTestEntityResource extends PostResource[String] {
  def get() = "test"
//  addToContext(ResourceContextId.LINK_TITLE, "create TestEntity");
//  def createEntityTemplate() = TestEntity(Some("1"), "hi", "content")
//  override def getEntity() = TestEntity(None, "","").asInstanceOf[Nothing]
//  def addEntity(entity: TestEntity): TestEntity = { null }
//  override def redirectTo() = super.redirectTo(classOf[TestEntitiesResource])
}

//class PutTestEntityResource extends PutEntityServerResource[TestEntity] {
//  def updateEntity(entity: TestEntity): TestEntity = {
//    ???
//  }
//}