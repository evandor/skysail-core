//package io.skysail.core.restlet.resources
//
//import io.skysail.core.restlet._
//import io.skysail.core.restlet.utils.ScalaResourceUtils
//import org.restlet.data.Method
//import org.restlet.data.Status
//import io.skysail.api.doc.ApiMetadata
//import io.skysail.core.model.LinkRelation
//import org.restlet.resource._
//import org.restlet.representation.Variant
//import io.skysail.core.restlet.responses.FormResponse
//import io.skysail.core.restlet.responses.ScalaSkysailResponse
//import org.restlet.data.Form
//import io.skysail.core.restlet.transformations.Transformations
//import org.json4s.DefaultFormats
//import org.json4s.jvalue2extractable
//import io.skysail.core.restlet.ScalaRequestHandler
//import io.skysail.core.restlet.ScalaResponseWrapper
//import io.skysail.core.restlet.ScalaSkysailBeanUtils
//import io.skysail.core.restlet.SkysailServerResource
//
//object PutEntityServerResource {
//  val GET_ENTITY_METHOD_NAME = "getEntity"
//  val UPDATE_ENTITY_METHOD_NAME = "updateEntity"
//}
//
//abstract class PutEntityServerResource[T: Manifest] extends SkysailServerResource[T] {
//
//  override def getLinkRelation() = LinkRelation.EDIT_FORM
//
//  override def getVerbs(): Set[Method] = Set(Method.GET, Method.PUT)
//  
//  def updateEntity(entity: T): T
//
//  @Get("htmlform|html|json")
//  def getPutTemplate(variant: Variant): FormResponse[T] = {
//
//    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "getPutTemplate")
//    val templatePaths = getSkysailApplication().getTemplatePaths(this.getClass())
//    val formTarget = templatePaths.stream().findFirst().orElse(".")
//    var entity: T = getEntity3()
//    timerMetric.stop()
//    new FormResponse[T](getResponse(), entity, ".?method=PUT" /*links(0).uri*/ )
//
//    //        AbstractResourceFilter<PutEntityServerResource<T>, T> chain = requestHandler.createForFormResponse();
//    //        ResponseWrapper<T> wrapper = chain.handle(this, getResponse());
//    //        return new FormResponse<>(getResponse(), wrapper.getEntity(), identifier, null, redirectBackTo());
//  }
//
//  @Put("x-www-form-urlencoded:html|json")
//  def put(form: Form, variant: Variant): ScalaSkysailResponse[T] = {
//    implicit val formats = DefaultFormats
//    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "put")
//    val json = Transformations.jsonFrom[T](form)
//    val e = json.extract[T]
//    //val createFrom = new FormDeserializer[T] (getParameterizedType()).createFrom(form);
//    val result = putJson(e, variant);
//    timerMetric.stop();
//    return result;
//  }
//
//  @Put("json")
//  def putJson(entity: T, variant: Variant): ScalaSkysailResponse[T] = {
//    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "putJson")
//    //        getRequest().getAttributes().put(SKYSAIL_SERVER_RESTLET_VARIANT, variant);
//    //        getRequest().getAttributes().put(SKYSAIL_SERVER_RESTLET_ENTITY, entity);
//    //        RequestHandler<T> requestHandler = new RequestHandler<>(getApplication());
//    //        AbstractResourceFilter<PutEntityServerResource<T>, T> handler = requestHandler.createForPut();
//    //        ResponseWrapper<T> handledRequest = handler.handle(this, getResponse());
//    val handledRequest = doPut(entity, variant)
//    timerMetric.stop();
//    //
//    //        if (handledRequest.getConstraintViolationsResponse() != null) {
//    //            return handledRequest.getConstraintViolationsResponse();
//    //        }
//    //        return new FormResponse<>(getResponse(), handledRequest.getEntity(),".");
//    new FormResponse[T](getResponse(), entity /*handledRequest.getEntity()*/ , ".")
//
//  }
//
//  def copyProperties(dest: T, orig: T): Unit = {
//    try {
//      val beanUtilsBean = new ScalaSkysailBeanUtils[T](orig, ScalaResourceUtils.determineLocale(this),
//        getSkysailApplication().getSkysailApplicationService())
//      beanUtilsBean.copyProperties(dest, orig, this)
//    } catch {
//      case e: Throwable => throw new RuntimeException("Error copying beans", e)
//    }
//  }
//
//  def redirectBackTo(): String = {
//    return null
//  }
//
//  def getEntity(): T = {
//    ???
//  }
//
//  override def getApiMetadata() = {
////    val apiMetadata = ApiMetadata.builder()
////    apiMetadata.summaryForGet(this.getClass(), PutEntityServerResource.GET_ENTITY_METHOD_NAME)
////    apiMetadata.descriptionForGet(this.getClass(), PutEntityServerResource.GET_ENTITY_METHOD_NAME)
////    apiMetadata.tagsForGet(this.getClass(), PutEntityServerResource.GET_ENTITY_METHOD_NAME)
////
////    apiMetadata.summaryForPut(this.getClass(), PutEntityServerResource.UPDATE_ENTITY_METHOD_NAME)
////    apiMetadata.descriptionForGet(this.getClass(), PutEntityServerResource.UPDATE_ENTITY_METHOD_NAME)
////    apiMetadata.tagsForGet(this.getClass(), PutEntityServerResource.UPDATE_ENTITY_METHOD_NAME)
////
////    apiMetadata.build()
//    null
//  }
//
//  def getEntity3() = {
//    val requestHandler = new ScalaRequestHandler[T](null.asInstanceOf[T], null)
//    val responseWrapper = requestHandler.createForGet().handle(this, getResponse())
//    responseWrapper.getEntity().asInstanceOf[T]
//  }
//
//  def doPut(entity: T, variant: Variant): ScalaResponseWrapper[T] = {
//    getResponse().setStatus(Status.SUCCESS_CREATED)
//    new ScalaRequestHandler[T](entity, variant).createForPut().handle(this, getResponse())
//  }
//
//}