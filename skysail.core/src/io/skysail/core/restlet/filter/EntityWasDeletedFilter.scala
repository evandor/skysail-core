package io.skysail.core.restlet.filter

import org.slf4j.LoggerFactory

import io.skysail.restlet.AbstractResourceFilter
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import io.skysail.restlet.ScalaResponseWrapper

class EntityWasDeletedFilter[T:Manifest]() extends AbstractResourceFilter[T] {

  override val log = LoggerFactory.getLogger(this.getClass())

  override def doHandle(resource: SkysailServerResource[_], responseWrapper:  Wrapper3): FilterResult = {
    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
    val infoMessage = resource.getClass().getSimpleName() + ".deleted.success";
    super.doHandle(resource, responseWrapper);
    FilterResult.CONTINUE;
  }
}