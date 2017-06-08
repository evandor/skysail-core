package io.skysail.core.restlet.filter

import io.skysail.restlet.AbstractResourceFilter
import org.slf4j.LoggerFactory
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import io.skysail.restlet.resources.PostEntityServerResource

class PersistEntityFilter[T:Manifest](entity: T) extends AbstractResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[PersistEntityFilter[T]])

  override def doHandle(resource: SkysailServerResource[_], responseWrapper:  Wrapper3): FilterResult = {
    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
    val response = responseWrapper.getResponse();
    resource.asInstanceOf[PostEntityServerResource[T]].addEntity(entity);
    //        val id = entity.asInstanceOf[T].getId();
    //        if (id != null) {
    //            response.setLocationRef(response.getRequest().getResourceRef().addSegment(id.replace("#", "")));
    //        }
    super.doHandle(resource, responseWrapper);
    return FilterResult.CONTINUE;
  }

}