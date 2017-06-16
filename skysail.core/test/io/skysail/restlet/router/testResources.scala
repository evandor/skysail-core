package io.skysail.restlet.router

import io.skysail.restlet.resources._
import io.skysail.core.restlet.resources.EntityServerResource
import io.skysail.core.restlet.resources.ListServerResource

case class TestEntity() {}

class TestEntityServerResource extends EntityServerResource[TestEntity] {
  def getEntity() = TestEntity()
}

class TestEntityListServerResource extends ListServerResource[List[TestEntity]] {
  def getEntity() = List(TestEntity())
}