package io.skysail.core.restlet.filter

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.junit._
import org.assertj.core.api.Assertions._
import io.skysail.core.model.ApplicationModel
import org.mockito.Mockito
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import io.skysail.core.ApiVersion
import io.skysail.core.model.TestEntitiesResource

@RunWith(classOf[JUnitRunner])
class EntityModelSpec extends FlatSpec with BeforeAndAfterEach {

//  var model: EntityModel = null
//
//  override def beforeEach() {
//    model = EntityModel(new TestEntity(Some("id"),"content").getClass())
//  }

  "A AddListheadersListFilter" should "not accept a null entity in constructor" in {
    val appModel = ApplicationModel("appName",new ApiVersion(1),List())
    appModel.addResourceModel("/list", classOf[TestEntitiesResource])
    val resource = new TestEntitiesResource()
    val responseWrapper = Mockito.mock(classOf[Wrapper3])
    val filter = new AddLinkheadersListFilter(appModel)
    filter.afterHandle(resource, responseWrapper)
  }
  
  

}