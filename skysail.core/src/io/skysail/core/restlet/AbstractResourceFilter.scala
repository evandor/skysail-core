package io.skysail.core.restlet

import org.restlet.Response
import org.slf4j.LoggerFactory
import org.restlet.Request
import io.skysail.core.restlet.resources._
import io.skysail.core.restlet.resources.EntityServerResource
import io.skysail.core.restlet.resources.PostEntityServerResource
import io.skysail.core.restlet.ScalaResponseWrapper

abstract class AbstractResourceFilter[T:Manifest] extends ResourceFilter[T] {

  override val log = LoggerFactory.getLogger(this.getClass())

  final def handle(resource: SkysailServerResource[_], response: Response): ScalaResponseWrapper[T] = {
    val responseWrapper = new ScalaResponseWrapper[T](response)
    handleMe(resource, responseWrapper)
    responseWrapper
  }

  protected def getDataFromRequest(request: Request, resource: SkysailServerResource[_]): Any = {
    val entityAsObject = request.getAttributes().get(SkysailServerResource.SKYSAIL_SERVER_RESTLET_ENTITY).asInstanceOf[T]
    if (entityAsObject != null) {
      if (resource.isInstanceOf[EntityServerResource[T]]) {
      } else if (resource.isInstanceOf[PostEntityServerResource[T]]) {
        return null //resource.asInstanceOf[PostEntityServerResource2[T]].getData(entityAsObject);
      }

      return null;
    }
    //        val form = (Form) request.getAttributes().get(EntityServerResource.SKYSAIL_SERVER_RESTLET_FORM);
    //        if (resource instanceof EntityServerResource) {
    //            return null;// ((EntityServerResource<T>) resource).getData(form);
    //        } else if (resource instanceof PostEntityServerResource) {
    //            return ((PostEntityServerResource<T>) resource).getData(form);
    //        } else if (resource instanceof PutEntityServerResource) {
    //            return null;// ((PutEntityServerResource<T>) resource).getData(form);
    //        } else if (resource instanceof PatchEntityServerResource) {
    //            return null;// ((PatchEntityServerResource<T>) resource).getData(form);
    ////        } else if (resource instanceof PostRelationResource) {
    ////            return null;//((PostRelationResource<?,?>) resource).getData(form);
    //        }

    null;
  }


}