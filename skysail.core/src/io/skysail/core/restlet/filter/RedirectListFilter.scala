package io.skysail.core.restlet.filter

import io.skysail.core.restlet.AbstractListResourceFilter
import org.slf4j.LoggerFactory
import io.skysail.core.restlet.SkysailServerResource
import io.skysail.core.restlet.Wrapper3

class RedirectListFilter[T <: List[_]]() extends AbstractListResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[AddLinkheadersListFilter[T]])

  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3) = {
    val redirectTo = resource.redirectTo();
    if (redirectTo != null) {
      val response = responseWrapper.getResponse();
      response.redirectSeeOther(redirectTo);
    }
  }
}