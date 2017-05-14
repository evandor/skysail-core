package io.skysail.restlet.resources

import io.skysail.restlet._
import io.skysail.restlet.utils.ScalaResourceUtils
import org.restlet.data.Method
import io.skysail.api.doc.ApiMetadata

object PutEntityServerResource {
  val GET_ENTITY_METHOD_NAME = "getEntity"
  val UPDATE_ENTITY_METHOD_NAME = "updateEntity"

}

class PutEntityServerResource[T] extends SkysailServerResource {

  override def getVerbs(): Set[Method] = Set(Method.GET, Method.PUT)

  def copyProperties(dest: T, orig: T): Unit = {
    try {
      val beanUtilsBean = new ScalaSkysailBeanUtils[T](orig, ScalaResourceUtils.determineLocale(this),
        getSkysailApplication().getSkysailApplicationService())
      beanUtilsBean.copyProperties(dest, orig, this)
    } catch {
      case e: Throwable => throw new RuntimeException("Error copying beans", e)
    }
  }

  def getEntity(): Any = {
    ???
  }

  override def getApiMetadata() = {
    val apiMetadata = ApiMetadata.builder()
    apiMetadata.summaryForGet(this.getClass(), PutEntityServerResource.GET_ENTITY_METHOD_NAME)
    apiMetadata.descriptionForGet(this.getClass(), PutEntityServerResource.GET_ENTITY_METHOD_NAME)
    apiMetadata.tagsForGet(this.getClass(), PutEntityServerResource.GET_ENTITY_METHOD_NAME)

    apiMetadata.summaryForPut(this.getClass(), PutEntityServerResource.UPDATE_ENTITY_METHOD_NAME)
    apiMetadata.descriptionForGet(this.getClass(), PutEntityServerResource.UPDATE_ENTITY_METHOD_NAME)
    apiMetadata.tagsForGet(this.getClass(), PutEntityServerResource.UPDATE_ENTITY_METHOD_NAME)

    apiMetadata.build()
  }

}