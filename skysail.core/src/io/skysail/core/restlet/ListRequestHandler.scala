//package io.skysail.core.restlet
//
//import org.restlet.data.Method
//import org.restlet.representation.Variant
//import io.skysail.core.model.ApplicationModel
//import io.skysail.core.restlet.filter._
//
//class ScalaListRequestHandler[T <: List[_]](variant: Variant, appModel: ApplicationModel) {
//
//  def createForList(method: Method): AbstractListResourceFilter[T] = {
//    if (method.equals(Method.GET)) {
//      return chainForListGet();
//    } else if (method.equals(Method.POST)) {
//      return null; //chainForListPost();
//    }
//    return null;
//    //  throw new RuntimeException("Method " + method + " is not yet supported");
//  }
//
//  private def chainForListGet(): AbstractListResourceFilter[T] = {
//    new ScalaExceptionCatchingListFilter[T]()
//      // .calling(new ExtractStandardQueryParametersResourceFilter<>())
//      .calling(new ScalaDataExtractingListFilter[T]())
//      .calling(new AddLinkheadersListFilter[T](appModel))
//      //      .calling(new SetExecutionTimeInListResponseFilter())
//      .calling(new RedirectListFilter[T]())
//      .asInstanceOf[AbstractListResourceFilter[T]]
//  }
//}