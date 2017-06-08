package io.skysail.restlet

import org.restlet.Response
import io.skysail.restlet.responses.ListResponse
import org.slf4j.LoggerFactory

abstract class AbstractListResourceFilter[T <: List[_]] extends ResourceFilter[T] {

  final def handle(resource: SkysailServerResource[_], response: Response): ListResponseWrapper[T] = {
    val responseWrapper = new ListResponseWrapper[T](response)
    handleMe(resource, responseWrapper)
    responseWrapper
  }
 
}