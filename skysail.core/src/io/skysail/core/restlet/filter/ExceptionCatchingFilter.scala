package io.skysail.core.restlet.filter

import io.skysail.restlet.AbstractResourceFilter
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import org.restlet.resource.ResourceException
import io.skysail.core.restlet.filter.ExceptionCatchingFilterHelper

class ExceptionCatchingFilter[T:Manifest] extends AbstractResourceFilter[T] {

  override def doHandle(resource: SkysailServerResource[_], responseWrapper:  Wrapper3): FilterResult = {
    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
    try {
      super.doHandle(resource, responseWrapper)
    } catch {
      case r: ResourceException => throw r
      case e: Exception => ExceptionCatchingFilterHelper.handleError(e, resource.getSkysailApplication(), responseWrapper, resource.getClass());
    }
    FilterResult.CONTINUE;
  }

  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): Unit = {
    resource.getServerInfo().setAgent("Skysail-Server/0.0.1 " + resource.getServerInfo().getAgent());
  }
}