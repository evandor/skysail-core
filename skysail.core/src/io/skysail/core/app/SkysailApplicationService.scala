package io.skysail.core.app

import org.osgi.service.component.annotations._
import java.util.ArrayList
import scala.collection.JavaConverters._
import io.skysail.restlet.services.EntityApi
import io.skysail.core.model.EntityModel
import io.skysail.core.model.APPLICATION_CONTEXT_RESOURCE
import io.skysail.core.model.ResourceModel

@Component(immediate = true, service = Array(classOf[SkysailApplicationService]))
class SkysailApplicationService {

  var entityApis: java.util.List[EntityApi] = new java.util.ArrayList[EntityApi]()

  /** --- mandatory reference ---------------------------*/
  @Reference(cardinality = ReferenceCardinality.MANDATORY)
  var applicationListProvider: ApplicationListProvider = null

  @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
  def addEntityApi(api: EntityApi): Unit = entityApis.add(api)

  def removeEntityApi(api: EntityApi): Unit = entityApis.remove(api)

  def getApplicationContextResources():List[ResourceModel] = {
    val allApps = applicationListProvider.getApplications()
    val appContextResourceClasses = allApps
      .map { app => app.associatedResourceClasses }
      .flatten
      .filter(association => association._1 == APPLICATION_CONTEXT_RESOURCE)
      .map(association => association._2)

    val allApplicationModels = allApps.map { app => app.getApplicationModel2() }

    val optionalResourceModels = for (
      appModel <- allApplicationModels;
      resClass <- appContextResourceClasses;
      val z = appModel.resourceModelFor(resClass)
    ) yield z

    optionalResourceModels
      .filter { m => m.isDefined }
      .map { m => m.get }
      .toList
  }
  
  def getEntityModel(name: String): EntityModel = {
    println(name) //io.skysail.app.notes.domain.Note
    val appModels = applicationListProvider.getApplications().map(a => a.getApplicationModel2()).toList
    println(appModels)
    null
  }

}