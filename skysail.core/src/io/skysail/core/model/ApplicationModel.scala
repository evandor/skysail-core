package io.skysail.core.model

import scala.collection.mutable.LinkedHashMap
import org.slf4j.LoggerFactory
import scala.collection.mutable.HashMap
import io.skysail.core.app.ApiVersion
import scala.None
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.model._
import io.skysail.core.akka.Resource
import akka.http.scaladsl.server.Directives._

/**
 * This is the root class of skysail's core domain, providing models of "skysail applications",
 * which aggregate controllers, their associated entities (together with the entities' fields), 
 * links between the resources and many more.
 * 
 * A real-life ApplictionModel is created by creating an instance and then adding controller models
 * together with their respective paths using "addControllerModel". A controller model describes the
 * controller responsible for the associated path together with relations amongst controllers. Furthermore,
 * it knows about the entity class related with the controller.
 * 
 *
 *  @constructor create a new application model, identified by its name.
 *
 *  @param name the application's (unique and descriptive) name
 *  @param apiVersion the applications API version, can be null
 *  @param associatedResourceClasses a list of associated Resource Classes together with the relation type.
 *
 */
case class ApplicationModel(
    val name: String,
    apiVersion: ApiVersion,
    val description: String,
    associatedResourceClasses: List[Tuple2[ResourceAssociationType, Class[_ <: Resource[_]]]] = List()) {

  require(name != null, "The application's name should be unique and must not be null")
  require(name.trim().length() > 0, "The application's name must not be empty")

  private val log = LoggerFactory.getLogger(this.getClass())

  /** The list of controllerModels of this applicationModel. */
  private val controllerModels = scala.collection.mutable.ListBuffer[ControllerModel]()

  /** The map between */
  private val entityModelsMap: LinkedHashMap[String, EntityModel] = scala.collection.mutable.LinkedHashMap()

  val appRoute: PathMatcher[Unit] = {
    log info s"attaching ${name} with apiVersion ${apiVersion}"
    if (apiVersion == null) PathMatcher(name) else name / apiVersion.toString()
  }

  /**
    * Adds a controller model for a given path.
    *
    * @param path
    * @param cls
    * @return
    */
  def addControllerModel(path: String, cls: Class[_ <: Resource[_]]): Option[Class[_]] = {
    require(path != null, "The resource's path must not be null")
    require(cls != null, "The resource's controller class must not be null")

    log info s"mapping '${appPath()}${path}' to '${cls}'"

    val controllerModel = new ControllerModel(/*this, */path, cls)

    if (controllerModels.filter(rm => rm.pathDefinition == controllerModel.pathDefinition).headOption.isDefined) {
      log.info(s"trying to add entity ${controllerModel.pathDefinition} again, ignoring...")
      return None
    }
    val entityClass = controllerModel.entityClass
    if (!entityModelsMap.get(entityClass.getName).isDefined) {
      entityModelsMap += entityClass.getName -> EntityModel(entityClass)
    }
    controllerModels += controllerModel
    build()
    Some(controllerModel.entityClass)
  }

  def controllerModelFor(cls: Class[_ <: Resource[_]]) = {
    controllerModels.filter { model => model.controllerClass == cls }.headOption
  }

  def getResourceModels(): List[ControllerModel] = controllerModels.toList

  def entityModelFor(cls: Class[_]) = entityModelsMap.get(cls.getName)

  def entityModelFor(ssr: Resource[_]): Option[EntityModel] = {
    val resModel = controllerModelFor(ssr.getClass)
    if (resModel.isEmpty) {
      None
    }

    entityModelsMap
      .map(e => e._2)
      .filter(v => v.entityClass == resModel.get.entityClass)
      .headOption
  }

  /**
   * @return the context path of the application, e.g. "/testapp/v2" or "/appwithoutversion".
   */
  def appPath() = "/" + name + (if (apiVersion != null) "/" + apiVersion.toString else "")

  private def printHtmlMap(map: scala.collection.mutable.Map[String, EntityModel]) = map.map(v => s"""
      <li>"${v._1}" -> ${v._2.toHtml}</li>""").mkString("")

  private def printMap(map: scala.collection.mutable.Map[String, EntityModel]) = map.map(v => s"""
      "${v._1}" -> ${v._2.toHtml}""").mkString("")

  private def build(): Unit = {
    controllerModels.foreach {
      resourceModel =>
        {
          resourceModel.linkModel = new LinkModel2(appPath(), resourceModel.pathDefinition, RESOURCE_SELF) //, resourceModel)//.resource, resourceModel.resource.getClass)
          var result = scala.collection.mutable.ListBuffer[LinkModel2]()
          resourceModel.linkModels = result.toList
        }
    }
  }
  

}