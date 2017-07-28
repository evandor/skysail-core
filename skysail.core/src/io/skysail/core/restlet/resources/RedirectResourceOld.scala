//package io.skysail.core.restlet.resources
//
//import io.skysail.core.restlet.SkysailServerResource
//import org.restlet.resource.Get
//import org.restlet.representation.Variant
//import io.skysail.core.restlet.responses.EntityResponse
//import org.restlet.data.Method
//
//abstract class RedirectResourceOld[T] extends SkysailServerResource[T] {
//
//  protected def redirectToResource(): SkysailServerResource[_]
//
//  override def getVerbs(): Set[Method] = Set(Method.GET)
//
//  @Get def redirectToEntity(variant: Variant): EntityResponse[T] = {
//    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "redirectToEntity")
//    //        if (variant != null) {
//    //            getRequest().getAttributes().put(SKYSAIL_SERVER_RESTLET_VARIANT, variant);
//    //        }
//    //
//    if (redirectToResource() == null) {
//      getResponse().redirectSeeOther(redirectTo());
//      timerMetric.stop();
//      return null;
//    }
//
//    // val link = LinkUtils.fromResource(getApplication(), redirectToResource());
//    // getPathSubstitutions().accept(link);
//    //  getResponse().redirectSeeOther(link.getUri());
//    timerMetric.stop()
//    new EntityResponse[T](getResponse(), null.asInstanceOf[T])
//  }
//}