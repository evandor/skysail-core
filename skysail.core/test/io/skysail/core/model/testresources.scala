package io.skysail.core.model

import io.skysail.core.restlet.resources
import io.skysail.core.restlet.resources._
import io.skysail.core.restlet.ResourceContextId

class TestEntitiesResource extends ListServerResource[List[TestEntity]](classOf[TestEntityResource]) { 
  def getEntity() = List(TestEntity(Some("23"),"hi", "content"))
  override def linkedResourceClasses() = List(classOf[PostTestEntityResource])
}

class TestEntityResource extends EntityServerResource[TestEntity] { 
  override def linkedResourceClasses() = List(classOf[PutTestEntityResource])

  def getEntity(): TestEntity = {
    TestEntity(Some("2"), "hi2", "content2")
  }
}

class PostTestEntityResource extends PostEntityServerResource[TestEntity] {
  addToContext(ResourceContextId.LINK_TITLE, "create TestEntity");
  def createEntityTemplate() = TestEntity(Some("1"), "hi", "content")
  override def getEntity() = TestEntity(None, "","").asInstanceOf[Nothing]
  def addEntity(entity: TestEntity): TestEntity = { null }
  override def redirectTo() = super.redirectTo(classOf[TestEntitiesResource])
}

class PutTestEntityResource extends PutEntityServerResource[TestEntity] {
  def updateEntity(entity: TestEntity): TestEntity = {
    ???
  }
}