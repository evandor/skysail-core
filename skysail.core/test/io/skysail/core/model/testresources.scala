package io.skysail.core.model

import akka.actor.ActorRef
import io.skysail.core.akka.actors._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.ClassTag

case class TestEntity(val foo: String)

class TestEntitiesResource extends ListResourceController[String] {
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = List("hi", "content")
  //override def linkedResourceClasses() = List(classOf[PostTestEntityResource])
}

class TestStringEntityController extends EntityResourceController[String] {
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {}
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
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = "test"
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

class TestEntityController extends EntityResourceController[TestEntity] {
  override protected def get[T](sender: ActorRef)(implicit c: ClassTag[T]): Unit = {}
}
