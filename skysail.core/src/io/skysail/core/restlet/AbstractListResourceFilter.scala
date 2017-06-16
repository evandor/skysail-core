package io.skysail.core.restlet

import org.restlet.Response

abstract class AbstractListResourceFilter[T <: List[_]] extends ResourceFilter[T] {

  final def handle(resource: SkysailServerResource[_], response: Response): ListResponseWrapper[T] = {
    val responseWrapper = new ListResponseWrapper[T](response)
    handleMe(resource, responseWrapper)
    responseWrapper
  }
 
}