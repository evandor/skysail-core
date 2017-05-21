package io.skysail.restlet.resources

import org.restlet.resource.Post
import org.restlet.representation.Variant
import org.restlet.data.Form
import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.collection.JavaConversions._
import scala.collection.mutable.MutableList
import org.restlet.resource.Get
import java.util.Arrays
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.responses.FormResponse
import io.skysail.restlet.responses.ScalaSkysailResponse
import org.restlet.data.Status
import io.skysail.restlet.ScalaRequestHandler
import io.skysail.restlet.ScalaResponseWrapper
import io.skysail.restlet.transformations.Transformations
import org.restlet.data.Method
import io.skysail.core.model.FORM_TARGET_RESOURCE
import io.skysail.core.model.LinkRelation
import io.skysail.api.doc.ApiMetadata

object PostEntityServerResource {
  val ADD_ENTITY_METHOD_NAME = "addEntity"
  val CREATE_ENTITY_TEMPLATE_METHOD_NAME = "createEntityTemplate"
}

abstract class PostEntityServerResource[T: Manifest] extends SkysailServerResource[T] {

  //   override def getLinks() = List(Link(".", relation = LinkRelation.NEXT, title = "form target", verbs = Set(Method.POST)))
  //override def associatedResourceClasses() = List((FORM_TARGET_RESOURCE,associatedEntiyResource))

  override def getLinkRelation() = LinkRelation.CREATE_FORM

  override def getVerbs(): Set[Method] = Set(Method.GET, Method.POST)

  def createEntityTemplate(): T

  def addEntity(entity: T): T

  def getEntity(): T = createEntityTemplate()

  @Get("htmlform|html")
  def getPostTemplate() = {
    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "getPostTemplate")
    val templatePaths = getSkysailApplication().getTemplatePaths(this.getClass())
    val formTarget = templatePaths.stream().findFirst().orElse(".")
    //val links = Arrays.asList(Link(formTarget))
    //links.stream().forEach(getPathSubstitutions())
    val entity: T = createEntityTemplate()
    //this.setEntity(entity)
    timerMetric.stop()
    new FormResponse[T](getResponse(), entity, "." /*links(0).uri*/ )
  }

  @Post("x-www-form-urlencoded:html")
  def post(form: Form, variant: Variant): ScalaSkysailResponse[T] = {
    implicit val formats = DefaultFormats
    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "posthtml")
    val json = if (form != null) Transformations.jsonFrom[T](form).extract[T] else null.asInstanceOf[T]
    val result = jsonPost(json, variant)
    timerMetric.stop()
    result
  }

  @Post("json")
  def jsonPost(entity: T, variant: Variant): ScalaSkysailResponse[T] = {
    val timerMetric = getMetricsCollector().timerFor(this.getClass(), "postjson")
    //getRequest().getAttributes().put(SKYSAIL_SERVER_RESTLET_VARIANT, variant)
    val handledRequest = doPost(entity, variant)
    timerMetric.stop()
    //    if (handledRequest.getConstraintViolationsResponse() != null) {
    //      return handledRequest.getConstraintViolationsResponse()
    //    }
    new FormResponse[T](getResponse(), entity /*handledRequest.getEntity()*/ , ".")
  }

  override def getApiMetadata() = {
    val apiMetadata = ApiMetadata.builder();
    apiMetadata.summaryForGet(this.getClass(), PostEntityServerResource.CREATE_ENTITY_TEMPLATE_METHOD_NAME);
    apiMetadata.descriptionForGet(this.getClass(), PostEntityServerResource.CREATE_ENTITY_TEMPLATE_METHOD_NAME);
    apiMetadata.tagsForGet(this.getClass(), PostEntityServerResource.CREATE_ENTITY_TEMPLATE_METHOD_NAME);

    apiMetadata.summaryForPost(this.getClass(), PostEntityServerResource.ADD_ENTITY_METHOD_NAME);
    apiMetadata.descriptionForPost(this.getClass(), PostEntityServerResource.ADD_ENTITY_METHOD_NAME);
    apiMetadata.tagsForPost(this.getClass(), PostEntityServerResource.ADD_ENTITY_METHOD_NAME);

    apiMetadata.build()

  }

  def doPost(entity: T, variant: Variant): ScalaResponseWrapper[T] = {
    getResponse().setStatus(Status.SUCCESS_CREATED)
    new ScalaRequestHandler[T](entity, variant).createForPost().handle(this, getResponse())
  }

}