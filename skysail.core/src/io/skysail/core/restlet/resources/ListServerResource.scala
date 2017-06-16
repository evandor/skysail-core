package io.skysail.core.restlet.resources

import org.restlet.representation.Variant
import org.restlet.resource.Get
import org.restlet.data.Method
import io.skysail.core.restlet.SkysailServerResource
import io.skysail.restlet.responses.ListResponse
import io.skysail.core.model.ENTITY_RESOURCE_FOR_LIST_RESOURCE
import io.skysail.api.doc.ApiMetadata
import io.skysail.core.restlet.ScalaListRequestHandler

object ListServerResource {
  val GET_ENTITY_METHOD_NAME = "getEntity";
  val ERASE_ENTITY_METHOD_NAME = "eraseEntity";
}

/**
 * Extend this class to create representations of Lists of Entities.
 * 
 *  
 */
abstract class ListServerResource[T <: List[_]](
    associatedEntityResource: Class[_ <: EntityServerResource[_]] = null) extends SkysailServerResource[T] {

  addAssociatedResourceClasses(List((ENTITY_RESOURCE_FOR_LIST_RESOURCE, associatedEntityResource)))

  override def getVerbs(): Set[Method] = Set(Method.GET)

  @Get("html|json|yaml|xml|csv|timeline|carbon|standalone|data")
  def getEntities(variant: Variant): ListResponse[List[T]] = {
    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "getEntities");
    val entitiesList = listEntities(variant);
    timerMetric.stop();
    new ListResponse[List[T]](getResponse(), entitiesList);
  }

  override def getApiMetadata(): ApiMetadata = {
    val apiMetadata = ApiMetadata.builder();

    apiMetadata.summaryForGet(this.getClass(), ListServerResource.GET_ENTITY_METHOD_NAME);
    apiMetadata.descriptionForGet(this.getClass(), ListServerResource.GET_ENTITY_METHOD_NAME);
    apiMetadata.tagsForGet(this.getClass(), ListServerResource.GET_ENTITY_METHOD_NAME);

    apiMetadata.summaryForDelete(this.getClass(), ListServerResource.ERASE_ENTITY_METHOD_NAME);
    apiMetadata.descriptionForGet(this.getClass(), ListServerResource.GET_ENTITY_METHOD_NAME);
    apiMetadata.tagsForGet(this.getClass(), ListServerResource.GET_ENTITY_METHOD_NAME);

    return apiMetadata.build();
  }

  private final def listEntities(variant: Variant): List[T] = {
    val appModel = getSkysailApplication().getApplicationModel2()
    val requestHandler = new ScalaListRequestHandler[T](variant, appModel)
    val responseWrapper = requestHandler.createForList(Method.GET).handle(this, getResponse())
    return responseWrapper.getEntity()
  }

}