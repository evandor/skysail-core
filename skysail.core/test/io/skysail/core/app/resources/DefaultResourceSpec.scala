//package io.skysail.core.app.resources
//
//import io.skysail.core.app._
//import org.assertj.core.api.Assertions._
//import org.junit._
//import org.mockito.Mockito
//import org.scalatest._
//import org.scalatest.junit.JUnitRunner
//import org.slf4j.LoggerFactory
//import org.junit.runner.RunWith
//import io.skysail.core.model.ResourceModel
//
//@RunWith(classOf[JUnitRunner])
//class DefaultResourceTest extends FlatSpec with BeforeAndAfterEach {
//
//  var app: SkysailApplication = _
//
//  override def beforeEach() = {
//    app = new SkysailRootApplication() {}
//    SkysailApplication.serviceListProvider = Mockito.mock(classOf[ScalaServiceListProvider])
//    val appService = Mockito.mock(classOf[SkysailApplicationService])
//    Mockito.when(app.getSkysailApplicationService()).thenReturn(appService)
//    val appContextResource = Mockito.mock(classOf[ResourceModel])
//    Mockito.when(appService.getApplicationContextResources()).thenReturn(List(appContextResource))
//    org.restlet.Application.setCurrent(app)
//  }
//
////  "The DefaultResource" should "redirect to null" in {
////    assertThat(new DefaultResource().redirectTo()).isEqualTo(null)
////  }
//  
//  "The DefaultResource" should "have no runtime links" in {
//    val runtimeLinks = new DefaultResource().runtimeLinks()
//    assertThat(runtimeLinks.size).isEqualTo(1)
//  }
//}