//package io.skysail.core.restlet.filter
//
//import io.skysail.core.restlet.AbstractListResourceFilter
//import io.skysail.core.restlet.SkysailServerResource
//import org.slf4j.LoggerFactory
//import org.restlet.resource.ResourceException
//import io.skysail.core.restlet.Wrapper3
//
//class ScalaExceptionCatchingListFilter[T <: List[_]] extends AbstractListResourceFilter[T] {
//
//  override val log = LoggerFactory.getLogger(classOf[ScalaExceptionCatchingListFilter[T]])
//
//  override def doHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): FilterResult = {
//    log.debug("entering {}#doHandle", this.getClass().getSimpleName());
//    try {
//      super.doHandle(resource, responseWrapper)
//    } catch {
//      case r: ResourceException => throw r
//      case e: Exception => ExceptionCatchingFilterHelper.handleError(e, resource.getSkysailApplication(), responseWrapper, resource.getClass());
//    }
//    FilterResult.CONTINUE;
//  }
//
//  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): Unit = {
//    resource.getServerInfo().setAgent("Skysail-Server/0.0.1 " + resource.getServerInfo().getAgent());
//  }
//}