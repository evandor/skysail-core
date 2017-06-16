package io.skysail.core.model

import scala.collection.mutable.LinkedHashMap
import org.slf4j.LoggerFactory
import scala.collection.mutable.HashMap
import io.skysail.core.restlet.SkysailServerResource
import io.skysail.core.ApiVersion
import scala.None
import org.restlet.Request

/**
 * This is the root class of skysail's core domain, describing an application,
 * which aggregates resources, their associated entities (together with the entities' fields)
 * and, finally, links between the resources.
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
    associatedResourceClasses: List[Tuple2[ResourceAssociationType, Class[_ <: SkysailServerResource[_]]]] = List()) {

  private val log = LoggerFactory.getLogger(this.getClass())

  require(name != null, "The application's name should be unique and must not be null")
  require(name.trim().length() > 0, "The application's name must not be empty")

  /** The list of resourceModels of this applicationModel. */
  private val resourceModels = scala.collection.mutable.ListBuffer[ResourceModel]()
  
  /** The map between */
  private val entityModelsMap: LinkedHashMap[String, EntityModel] = scala.collection.mutable.LinkedHashMap()

  /**
   * adds an non-null resource model identified by its path.
   *
   * If a resource model with the same name exists already, this method
   * returns None, otherwise it returns "Some('the resources entity class')"
   *
   * Otherwise, the resource model will be added to the map of managed resources.
   */
  def addResourceModel(path: String, cls: Class[_ <: io.skysail.core.restlet.SkysailServerResource[_]]): Option[Class[_]] = {
    require(path != null, "The resource's path can be empty, but must not be null")
    val resourceModel = new ResourceModel(this, path, cls)
    if (resourceModels.filter(rm => rm.path == resourceModel.path).headOption.isDefined) {
      log.info(s"trying to add entity ${resourceModel.path} again, ignoring...")
      return None
    }
    val entityClass = resourceModel.entityClass
    if (!entityModelsMap.get(entityClass.getName).isDefined) {
      entityModelsMap += entityClass.getName -> EntityModel(entityClass)
    }
    resourceModels += resourceModel
    build()
    Some(resourceModel.entityClass)
  }

  def resourceModelFor(cls: Class[_ <: SkysailServerResource[_]]) = {
    //log.info(s"resourceModelFor($cls): checking ${resourceModels.map(m => m.targetResourceClass).mkString(";")}")
    resourceModels.filter { model => model.targetResourceClass == cls }.headOption
  }

  def entityModelFor(cls: Class[_]) = entityModelsMap.get(cls.getName)

  def entityModelFor(ssr: io.skysail.core.restlet.SkysailServerResource[_]): Option[EntityModel] = {
    val resModel = resourceModelFor(ssr.getClass)
    if (resModel.isEmpty) {
        None
    }
    
    entityModelsMap
        .map(e => e._2)
        .filter(v =>  v.entityClass == resModel.get.entityClass)
        .headOption
  }

  def linksFor(resourceClass: Class[_ <: io.skysail.core.restlet.SkysailServerResource[_]]): List[LinkModel] = {
    val r = resourceModels.filter { resourceModel => resourceModel.resource.getClass == resourceClass }.headOption
    if (r.isDefined) r.get.linkModels else List()
  }

  def toHtml(request: Request) = s"""<b>${this.getClass.getSimpleName}</b>("$name","$apiVersion")<br><br>
    &nbsp;&nbsp;&nbsp;<u>Entities</u>: <ul>${printHtmlMap(entityModelsMap)}</ul>
    &nbsp;&nbsp;&nbsp;<u>Resources</u>: <ul>${resourceModels.map { v => "<li>" + v.toHtml(name, apiVersion, request) + "</li>" }.mkString("")}</ul>"""

  override def toString() = s"""${this.getClass.getSimpleName}("$name","$apiVersion")
    Resources: ${resourceModels.map { v => sys.props("line.separator") + " " * 6 + " - " + v }.mkString("")}
    Entities: ${printMap(entityModelsMap)}"""

  private def printHtmlMap(map: scala.collection.mutable.Map[String, EntityModel]) = map.map(v => s"""
      <li>"${v._1}" -> ${v._2.toHtml}</li>""").mkString("")

  private def printMap(map: scala.collection.mutable.Map[String, EntityModel]) = map.map(v => s"""
      "${v._1}" -> ${v._2.toHtml}""").mkString("")

  private def appPath() = "/" + name + (if (apiVersion != null) apiVersion.getVersionPath() else "")

  private def build(): Unit = {
    resourceModels.foreach {
      resourceModel =>
        {
          resourceModel.linkModel = new LinkModel(appPath(), resourceModel.path, RESOURCE_SELF, resourceModel.resource, resourceModel.resource.getClass)
          var result = scala.collection.mutable.ListBuffer[LinkModel]()
          resourceModel.resource.linkedResourceClasses().foreach {
            lrCls =>
              {
                val res = resourceModelFor(lrCls)
                if (res.isDefined) {
                  result += new LinkModel(appPath(), res.get.path, LINKED_RESOURCE, res.get.resource, lrCls)
                }
              }
          }
          val associatedResourceModels = resourceModel.resource.associatedResourceClasses
            .map(r => resourceModelFor(r._2))
            .filter(r => r.isDefined)
            .map(r => r.get)
            .toList

          associatedResourceModels.foreach { resModel =>
            {
              result += new LinkModel(appPath(), resModel.path, LINKED_RESOURCE, resModel.resource, resModel.resource.getClass)
            }
          }
          resourceModel.linkModels = result.toList
        }
    }
  }

}