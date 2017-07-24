//package io.skysail.core.restlet.filter
//
//import collection.mutable.Stack
//import org.scalatest._
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//import org.junit._
//import org.assertj.core.api.Assertions._
//import io.skysail.core.model.ApplicationModel
//import org.mockito.Mockito
//import io.skysail.core.restlet.SkysailServerResource
//import io.skysail.core.ApiVersion
//import io.skysail.core.restlet.utils.ScalaHeadersUtils
//import org.restlet._
//import io.skysail.core.model._
//import io.skysail.core.restlet.Wrapper3
//
//@RunWith(classOf[JUnitRunner])
//class AddLinkheadersListFilterSpec extends FlatSpec with BeforeAndAfterEach {
//
//  var appModel: ApplicationModel = null
//  var resource: SkysailServerResource[_] = null
//  var request: Request = null
//  var response: Response = null
//  var responseWrapper: Wrapper3 = null
//
//  override def beforeEach() {
//    appModel = ApplicationModel("appName", new ApiVersion(1), List())
//    appModel.addResourceModel("/list", classOf[TestEntitiesResource])
//    appModel.addResourceModel("/list/{id}/name/{name}", classOf[TestEntityResource])
//    resource = new TestEntitiesResource()
//    request = new Request()
//    response = new Response(request)
//    resource.init(null, request, response)
//    responseWrapper = Mockito.mock(classOf[Wrapper3])
//  }
//
//  "A AddListheadersListFilter" should "adds the appropriate linkheaders to the response headers" in {
//    val filter = new AddLinkheadersListFilter(appModel)
//    filter.afterHandle(resource, responseWrapper)
//    val linkheader = ScalaHeadersUtils.getHeaders(resource.getResponse()).getFirstValue("Link")
//    assertThat(linkheader).contains("/list/23")
//  }
//
//}