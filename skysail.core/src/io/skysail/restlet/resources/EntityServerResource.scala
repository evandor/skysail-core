package io.skysail.restlet.resources

import io.skysail.restlet.SkysailServerResource
import org.restlet.data.Method
import io.skysail.core.model.LinkRelation
import io.skysail.restlet.ResourceContextId
import org.restlet.resource.Get
import org.restlet.representation.Variant
import io.skysail.restlet.responses.EntityResponse
import io.skysail.restlet.ScalaListRequestHandler
import io.skysail.restlet.ScalaRequestHandler
import io.skysail.api.doc.ApiMetadata
import io.skysail.restlet.responses.ScalaSkysailResponse
import org.restlet.resource.Delete

object EntityServerResource {
  val GET_ENTITY_METHOD_NAME = "getEntity"
  val ERASE_ENTITY_METHOD_NAME = "eraseEntity"
}

abstract class EntityServerResource[T: Manifest] extends SkysailServerResource[T] {

  addToContext(ResourceContextId.LINK_TITLE, "show");

  def eraseEntity() = null//new ScalaSkysailResponse[T](){}

  override def getVerbs(): Set[Method] = Set(Method.GET)

  override def getLinkRelation() = LinkRelation.ITEM

  // input: html|json|..., output: html|json|...
  /**
   * @return the response
   */
  @Get("html|json|eventstream|treeform|txt|csv|yaml|mailto|data")
  def getResource(variant: Variant): EntityResponse[T] = {
    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "getResource");
    //        if (variant != null) {
    //            getRequest().getAttributes().put(SKYSAIL_SERVER_RESTLET_VARIANT, variant);
    //        }
    val entity = getEntity3();
    timerMetric.stop();
    new EntityResponse[T](getResponse(), entity);
  }

  //    @Get("htmlform")
  //    def SkysailResponse<T> getDeleteForm() {
  //        return new FormResponse<>(getResponse(), getEntity("dummy"), ".", "/");
  //    }

   @Delete("x-www-form-urlencoded:html|html|json")
    def  deleteEntity(variant: Variant): EntityResponse[T] = {
    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "deleteEntity");
//        if (variant != null) {
//            getRequest().getAttributes().put(SKYSAIL_SERVER_RESTLET_VARIANT, variant);
//        }
        //val requestHandler = new RequestHandler<>(getApplication());
        val requestHandler = new ScalaRequestHandler[T](null.asInstanceOf[T], null) 
        //AbstractResourceFilter<EntityServerResource<T>, T> handler = requestHandler.createForEntity(Method.DELETE);
        val responseWrapper = requestHandler.createForDelete().handle(this, getResponse())
        //T entity = handler.handle(this, getResponse()).getEntity();
        timerMetric.stop();
        new EntityResponse[T](getResponse(), entity);
    }

  override def getApiMetadata() = {
    val apiMetadata = ApiMetadata.builder()
    apiMetadata.summaryForGet(this.getClass(), EntityServerResource.GET_ENTITY_METHOD_NAME);
    apiMetadata.descriptionForGet(this.getClass(), EntityServerResource.GET_ENTITY_METHOD_NAME);
    apiMetadata.tagsForGet(this.getClass(), EntityServerResource.GET_ENTITY_METHOD_NAME);

    apiMetadata.summaryForDelete(this.getClass(), EntityServerResource.ERASE_ENTITY_METHOD_NAME);
    apiMetadata.descriptionForGet(this.getClass(), EntityServerResource.GET_ENTITY_METHOD_NAME);
    apiMetadata.tagsForGet(this.getClass(), EntityServerResource.GET_ENTITY_METHOD_NAME);

    apiMetadata.build()
  }

  final def getEntity3(): T = {
    val requestHandler = new ScalaRequestHandler[T](null.asInstanceOf[T], null) //, appModel)
    val responseWrapper = requestHandler.createForGet().handle(this, getResponse())
    responseWrapper.getEntity().asInstanceOf[T]
  }

 
}