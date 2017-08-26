package io.skysail.core.model

import scala.collection.mutable.LinkedHashMap
import org.slf4j.LoggerFactory
import scala.collection.mutable.HashMap
import io.skysail.core.app.ApiVersion
import scala.None
import akka.http.scaladsl.server.PathMatcher
import io.skysail.core.model._
import io.skysail.core.akka.ResourceController
import akka.http.scaladsl.server.Directives._

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
    val description: String,
    associatedResourceClasses: List[Tuple2[ResourceAssociationType, Class[_ <: ResourceController[_]]]] = List()) {

  private val log = LoggerFactory.getLogger(this.getClass())

  require(name != null, "The application's name should be unique and must not be null")
  require(name.trim().length() > 0, "The application's name must not be empty")

  val appRoute = {
    log info s"attaching ${name} with apiVersion ${apiVersion}"
    if (apiVersion == null) PathMatcher(name) else name / apiVersion.toString()
  }

  /** The list of resourceModels of this applicationModel. */
  private val resourceModels = scala.collection.mutable.ListBuffer[ResourceModel2]()

  /** The map between */
  private val entityModelsMap: LinkedHashMap[String, EntityModel] = scala.collection.mutable.LinkedHashMap()

  def addResourceModel(path: String, cls: Class[_ <: ResourceController[_]]): Option[Class[_]] = {
    require(path != null, "The resource's path must not be null")

    log info s"mapping '${appPath()}${path}' to '${cls}'"

    /*val resourceModel2 =
      path.trim() match {
        case "" => new ResourceModel2(this, appRoute, cls)
        case "/" => new ResourceModel2(this, appRoute / , cls)
        //case x if (x.endsWith("*")) => new ResourceModel2(this, pathPrefix(appRoute) , cls)
        case _ => new ResourceModel2(this, appRoute / getMatcher(path), cls)
      }*/

    val resourceModel2 = new ResourceModel2(this, /*appRoute / */path, cls)
    if (resourceModels.filter(rm => rm.pathMatcher == resourceModel2.pathMatcher).headOption.isDefined) {
      log.info(s"trying to add entity ${resourceModel2.pathMatcher} again, ignoring...")
      return None
    }
    val entityClass = resourceModel2.entityClass
    if (!entityModelsMap.get(entityClass.getName).isDefined) {
      entityModelsMap += entityClass.getName -> EntityModel(entityClass)
    }
    //log info s"creating route for ${akkaModel.name}/${m.pathMatcher}"

    resourceModels += resourceModel2
    build()
    Some(resourceModel2.entityClass)
  }

  def resourceModelFor(cls: Class[_ <: ResourceController[_]]) = {
    resourceModels.filter { model => model.targetResourceClass == cls }.headOption
  }

  def getResourceModels(): List[ResourceModel2] = {
    resourceModels.toList
  }

  def entityModelFor(cls: Class[_]) = entityModelsMap.get(cls.getName)

  def entityModelFor(ssr: ResourceController[_]): Option[EntityModel] = {
    val resModel = resourceModelFor(ssr.getClass)
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

  //  def linksFor(resourceClass: Class[_ <: io.skysail.core.restlet.SkysailServerResource[_]]): List[LinkModel2] = {
  ////    val r = resourceModels.filter { resourceModel => resourceModel.resource.getClass == resourceClass }.headOption
  ////    if (r.isDefined) r.get.linkModels else List()
  //    List()
  //  }

  //  def toHtml(request: Request) = s"""<b>${this.getClass.getSimpleName}</b>("$name","$apiVersion")<br><br>
  //    &nbsp;&nbsp;&nbsp;<u>Entities</u>: <ul>${printHtmlMap(entityModelsMap)}</ul>
  //    &nbsp;&nbsp;&nbsp;<u>Resources</u>: <ul>${resourceModels.map { v => "<li>" + v.toHtml(name, apiVersion, request) + "</li>" }.mkString("")}</ul>"""

  override def toString() = "xxx"
  //    s"""${this.getClass.getSimpleName}("$name","$apiVersion")
  //    Resources: ${resourceModels.map { v => sys.props("line.separator") + " " * 6 + " - " + v }.mkString("")}
  //    Entities: ${printMap(entityModelsMap)}"""

  private def printHtmlMap(map: scala.collection.mutable.Map[String, EntityModel]) = map.map(v => s"""
      <li>"${v._1}" -> ${v._2.toHtml}</li>""").mkString("")

  private def printMap(map: scala.collection.mutable.Map[String, EntityModel]) = map.map(v => s"""
      "${v._1}" -> ${v._2.toHtml}""").mkString("")

  private def build(): Unit = {
    resourceModels.foreach {
      resourceModel =>
        {
          resourceModel.linkModel = new LinkModel2(appPath(), resourceModel.pathMatcher, RESOURCE_SELF) //, resourceModel)//.resource, resourceModel.resource.getClass)
          var result = scala.collection.mutable.ListBuffer[LinkModel2]()
          //          resourceModel.resource.linkedResourceClasses().foreach {
          //            lrCls =>
          //              {
          //                val res = resourceModelFor(lrCls)
          //                if (res.isDefined) {
          //                  result += new LinkModel2(appPath(), res.get.pathMatcher, LINKED_RESOURCE, res.get.resource, lrCls)
          //                }
          //              }
          //          }
          //          val associatedResourceModels = resourceModel.resource.associatedResourceClasses
          //            .map(r => resourceModelFor(r._2))
          //            .filter(r => r.isDefined)
          //            .map(r => r.get)
          //            .toList
          //
          //          associatedResourceModels.foreach { resModel =>
          //            {
          //              result += new LinkModel2(appPath(), resModel.pathMatcher, LINKED_RESOURCE, resModel.resource, resModel.resource.getClass)
          //            }
          //          }
          resourceModel.linkModels = result.toList
        }
    }
  }
  

}