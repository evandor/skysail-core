package io.skysail.restlet.router

import io.skysail.restlet.resources._

case class TestEntity() {}

class TestEntityServerResource extends EntityServerResource[TestEntity] {
  def getEntity() = TestEntity()
}

class TestEntityListServerResource extends ListServerResource[List[TestEntity]] {
  def getEntity() = List(TestEntity())
}