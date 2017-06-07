package io.skysail.restlet.filter

import io.skysail.restlet.ScalaAbstractResourceFilter
import org.slf4j.LoggerFactory
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import io.skysail.restlet.resources.PostEntityServerResource
import io.skysail.restlet.ScalaResponseWrapper
import io.skysail.restlet.resources.PutEntityServerResource

class UpdateEntityFilter[T:Manifest](entity: T) extends ScalaAbstractResourceFilter[T] {

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