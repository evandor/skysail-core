package io.skysail.core.model

import scala.collection.JavaConverters._
import java.lang.reflect.Field
import io.skysail.core.restlet.utils.ScalaReflectionUtils
import io.skysail.core.restlet.SkysailServerResource
import io.skysail.core.restlet.router.SkysailRouter
import io.skysail.core.restlet.resources._
import org.slf4j.LoggerFactory
import org.restlet.Request
import io.skysail.core.ApiVersion
import io.skysail.core.restlet.resources.EntityServerResource
import io.skysail.core.restlet.resources.PostEntityServerResource
import io.skysail.core.restlet.resources.PutEntityServerResource
import io.skysail.core.restlet.resources.ListServerResource

/**
 * A ResourceModel captures the link between a path and a SkysailServerResource, defining
 * which path should be handled by which resource.
 *
 *  @constructor create a new resource model, given a path and a SkysailServerResource.
 *
 *  @param path the uri path relative to the application
 *  @param targetClass a SkysailServerResource class to handle requests to the given path
 */
case class ResourceModel(
    appModel: ApplicationModel, 
    val path: String, 
    val targetResourceClass: Class[_ <: SkysailServerResource[_]]) {

  require(path != null, "A ResourceModel's path must not be null")
  require(targetResourceClass != null, "A ResourceModel's target class must not be null")

  private val log = LoggerFactory.getLogger(this.getClass())
  
  // akka evaluation
 // def akkaRoute = new AkkaRouteProvider(path)

  val resource: SkysailServerResource[_] = targetResourceClass.newInstance().asInstanceOf[SkysailServerResource[_]]
  val entityClass: Class[_] = SkysailRouter.getResourcesGenericType(resource)
  lazy val pathVariables = getPathVariables(path)

  var linkModel: LinkModel = _
  var linkModels: List[LinkModel] = List()
  
  def getUri() = appModel.name + appModel.apiVersion.getVersionPath() + path
  
  def resourceType() = {
    resource match {
      case _: ListServerResource[_] => LIST_RESOURCE
      case _: EntityServerResource[_] => ENTITY_RESOURCE
      case _: PutEntityServerResource[_] => UPDATE_ENTITY_RESOURCE
      case _: PostEntityServerResource[_] => CREATE_ENTITY_RESOURCE
      case _ => UNSPECIFIED_RESOURCE
    }
  }

  def toHtml(name: String, apiVersion: ApiVersion, request: Request) = {
    val contextAndPath = s"/${name}${apiVersion.getVersionPath()}$path"
    val isCurrentResource = request.getResourceRef.getPath == contextAndPath
    val style = if (isCurrentResource) "background-color: yellow" else ""
    s"""<font style="$style"><b>${targetResourceClass.getSimpleName}</b>[${entityClass.getSimpleName}]: (<a href='$contextAndPath'>"$path"</a>)</font><br>
        <br><u>Links</u>: <ul>${linkModels.map { v => "<li>" + v + "</li>"}.mkString("")}</ul><br>"""
  }
  
  override def toString() = s""""$path": ${targetResourceClass.getSimpleName}[${entityClass.getSimpleName}]
        Links: ${linkModels.map { v => sys.props("line.separator") + " " * 10 + v }.mkString("")}"""
  //    Entities: ${printMap(entitiesMap)}"""

  private def printList(list: List[_]) = list.map(v => v).mkString("")

  private def getPathVariables(path: String) = 
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map { (e => e.toString().replace("{", "").replace("}", "")) }
      .toList
  

}