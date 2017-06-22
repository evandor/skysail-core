package io.skysail.core.app

import collection.mutable.Stack
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import org.mockito.Mockito
import io.skysail.api.um.AuthenticationService
import io.skysail.core.restlet.SkysailServerResource
import io.skysail.core.app.resources.ModelResource

@RunWith(classOf[JUnitRunner])
class SkysailApplicationSpec extends FlatSpec with BeforeAndAfterEach {
  
  var app: SkysailApplication = _
  
  override def beforeEach() = {
    app = new SkysailApplication("testApp"){ }
    SkysailApplication.serviceListProvider = Mockito.mock(classOf[ScalaServiceListProvider])
    val mockedAuthService = Mockito.mock(classOf[AuthenticationService])
    Mockito.when(SkysailApplication.serviceListProvider.getAuthenticationService()).thenReturn(mockedAuthService)    
  }

  "A SkysailApplication" should "provide a ModelResource describing the underlying Application Model" in {
    app.createInboundRoot()
    val resourceModels = app.getApplicationModel2().resourceModelFor(classOf[ModelResource])
    assert(resourceModels.size == 1)
    assert(resourceModels.head.path == "/_model!")
  }
}