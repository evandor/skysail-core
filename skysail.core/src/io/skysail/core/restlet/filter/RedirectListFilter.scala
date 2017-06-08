package io.skysail.core.restlet.filter

import io.skysail.restlet.ScalaAbstractListResourceFilter
import org.slf4j.LoggerFactory
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import io.skysail.core.restlet.filter.AddLinkheadersListFilter

class RedirectListFilter[T <: List[_]]() extends ScalaAbstractListResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[AddLinkheadersListFilter[T]])

  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3) = {
    val redirectTo = resource.redirectTo();
    if (redirectTo != null) {
      val response = responseWrapper.getResponse();
      response.redirectSeeOther(redirectTo);
    }
  }
}