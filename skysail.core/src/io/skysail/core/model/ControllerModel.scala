package io.skysail.core.model

import scala.collection.JavaConverters._
import java.lang.reflect.Field
import io.skysail.core.ScalaReflectionUtils
import org.slf4j.LoggerFactory
import io.skysail.core.app.ApiVersion
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.akka.ResourceController
import scala.reflect.ClassTag

/**
 * A ControllerModel connects a pathDefinition with a ResourceController class
 * which will handle specific requests to this path.
 *
 * The HTTP verbs which will be handled depend on the concrete subclass of the ResourceController provided.
 *
 *  @param pathDefinition e.g. "/somepath/:id". This will be mapped to an akka route.
 *  @param controllerClass a concrete subclass of ResourceController which will handle the requests to the
 *                         associated path.
 */
case class ControllerModel[T:ClassTag](
    //appModel: ApplicationModel, 
    val pathDefinition: String,
    val controllerClass: Class[_ <: ResourceController[T]]) {

  require(pathDefinition != null, "A ResourceModel's pathMatcher must not be null")
  require(controllerClass != null, "A ResourceModel's target class must not be null")

  private val log = LoggerFactory.getLogger(this.getClass())

  val entityClass: Class[_] = {
    val ctag = implicitly[reflect.ClassTag[T]]
    //val ctag = implicitly[reflect.ClassTag[String]]
    //val xxx = ctag.runtimeClass.asInstanceOf[Class[String]]
    
    println(ctag)
    val tp = ctag.runtimeClass.getTypeParameters
    println("xxx" + tp(0))
//    println(controllerClass.newInstance())
//    println(controllerClass.newInstance().entityClass())
//    controllerClass.newInstance().entityClass()
    "s".getClass
  }

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

  private def printList(list: List[_]) = list.map(v => v).mkString("")

  private def getPathVariables(path: String) =
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map { (e => e.toString().replace("{", "").replace("}", "")) }
      .toList

}