package io.skysail.core.restlet.filter

import io.skysail.core.restlet.AbstractResourceFilter
import org.slf4j.LoggerFactory
import io.skysail.core.restlet.SkysailServerResource
import io.skysail.core.restlet.resources.PutEntityServerResource
import io.skysail.core.restlet.Wrapper3

class UpdateEntityFilter[T:Manifest](entity: T) extends AbstractResourceFilter[T] {

  override val log = LoggerFactory.getLogger(this.getClass())

  override def doHandle(resource: SkysailServerResource[_], responseWrapper:  Wrapper3): FilterResult = {
    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
    val response = responseWrapper.getResponse();
    resource.asInstanceOf[PutEntityServerResource[T]].updateEntity(entity);
    //        val id = entity.asInstanceOf[T].getId();
    //        if (id != null) {
    //            response.setLocationRef(response.getRequest().getResourceRef().addSegment(id.replace("#", "")));
    //        }
    super.doHandle(resource, responseWrapper);
    return FilterResult.CONTINUE;
  }
  
//        Object entity = responseWrapper.getEntity();
//        if (entity != null) {
//            resource.updateEntity((T)entity);
//            SkysailResponse<T> response = new SkysailResponse<>();
//            responseWrapper.setEntity((response.getEntity()));
//            resource.setCurrentEntity(response.getEntity());
//        }
//        super.doHandle(resource, responseWrapper);
//        return FilterResult.CONTINUE;

}