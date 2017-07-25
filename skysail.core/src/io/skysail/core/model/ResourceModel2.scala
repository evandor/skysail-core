package io.skysail.core.model

import scala.collection.JavaConverters._
import java.lang.reflect.Field
import io.skysail.core.restlet.utils.ScalaReflectionUtils
import io.skysail.core.restlet.resources._
import org.slf4j.LoggerFactory
import io.skysail.core.app.ApiVersion
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.akka.ResourceActor

case class ResourceModel2(
    appModel: ApplicationModel, 
    val pathMatcher: PathMatcher[Unit],
    val targetResourceClass: Class[_ <: ResourceActor[_]]) {

  require(pathMatcher != null, "A ResourceModel's pathMatcher must not be null")
  require(targetResourceClass != null, "A ResourceModel's target class must not be null")

  private val log = LoggerFactory.getLogger(this.getClass())
  
  //val resource: ResourceDefinition[_] = targetResourceClass.newInstance().asInstanceOf[ResourceDefinition[_]]
  val entityClass: Class[_] = {
    import scala.reflect.runtime.universe._
    val tt = targetResourceClass.getTypeParameters
    val str = tt.map(ttt => ttt.toString()).mkString(";")
    println("*** " + targetResourceClass + ": " + str)
    /*val p = ScalaReflectionUtils.getParameterizedType(targetResourceClass)//SkysailRouter.getResourcesGenericType(resource)
    p*/
    "s".getClass
  }
 // lazy val pathVariables = getPathVariables(path)

  var linkModel: LinkModel2 = _
  var linkModels: List[LinkModel2] = List()
  
 // def getUri() = appModel.name + appModel.apiVersion.getVersionPath() + path
  
  def resourceType() = {
//    resource match {
//      case _: ListServerResource[_] => LIST_RESOURCE
//      case _: EntityServerResource[_] => ENTITY_RESOURCE
//      case _: PutEntityServerResource[_] => UPDATE_ENTITY_RESOURCE
//      case _: PostEntityServerResource[_] => CREATE_ENTITY_RESOURCE
//      case _ => UNSPECIFIED_RESOURCE
//    }
    LIST_RESOURCE
  }

//  def toHtml(name: String, apiVersion: ApiVersion, request: Request) = {
////    val contextAndPath = s"/${name}${apiVersion.getVersionPath()}$path"
////    val isCurrentResource = request.getResourceRef.getPath == contextAndPath
////    val style = if (isCurrentResource) "background-color: yellow" else ""
////    s"""<font style="$style"><b>${targetResourceClass.getSimpleName}</b>[${entityClass.getSimpleName}]: (<a href='$contextAndPath'>"$path"</a>)</font><br>
////        <br><u>Links</u>: <ul>${linkModels.map { v => "<li>" + v + "</li>"}.mkString("")}</ul><br>"""
//    "to be done"
//  }
  
//  override def toString() = s""""$path": ${targetResourceClass.getSimpleName}[${entityClass.getSimpleName}]
//        Links: ${linkModels.map { v => sys.props("line.separator") + " " * 10 + v }.mkString("")}"""

  private def printList(list: List[_]) = list.map(v => v).mkString("")

  private def getPathVariables(path: String) = 
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map { (e => e.toString().replace("{", "").replace("}", "")) }
      .toList
  

}