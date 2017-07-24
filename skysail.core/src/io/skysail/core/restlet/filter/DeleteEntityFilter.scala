//package io.skysail.core.restlet.filter
//
//import io.skysail.core.restlet.AbstractResourceFilter
//import org.slf4j.LoggerFactory
//import io.skysail.core.restlet.SkysailServerResource
//import io.skysail.core.restlet.Wrapper3
//
//class DeleteEntityFilter[T: Manifest] extends AbstractResourceFilter[T] {
//
//  override val log = LoggerFactory.getLogger(this.getClass())
//
//  override def doHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): FilterResult = {
//    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
//    //resource.eraseEntity();
//    super.doHandle(resource, responseWrapper);
//    return FilterResult.CONTINUE;
//  }
//}