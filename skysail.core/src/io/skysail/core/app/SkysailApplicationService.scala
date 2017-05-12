package io.skysail.core.app

import org.osgi.service.component.annotations._
import java.util.ArrayList
import scala.collection.JavaConverters._
import io.skysail.restlet.services.EntityApi
import io.skysail.core.model.EntityModel

@Component(immediate = true, service = Array(classOf[SkysailApplicationService]))
class SkysailApplicationService {

  var entityApis: java.util.List[EntityApi] = new java.util.ArrayList[EntityApi]()

  /** --- mandatory reference ---------------------------*/
  @Reference(cardinality = ReferenceCardinality.MANDATORY)
  var applicationListProvider: ApplicationListProvider = null

  @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
  def addEntityApi(api: EntityApi): Unit = entityApis.add(api)

  def removeEntityApi(api: EntityApi): Unit = entityApis.remove(api)

  def getEntityModel(name: String): EntityModel = {
    println(name) //io.skysail.app.notes.domain.Note
    val appModels = applicationListProvider.getApplications().map(a => a.getApplicationModel2()).toList
    println(appModels)
    null

  }

}