package io.skysail.core.restlet.filter

import org.slf4j.LoggerFactory

import io.skysail.restlet.AbstractResourceFilter
import io.skysail.restlet.SkysailServerResource
import io.skysail.restlet.Wrapper3
import org.restlet.data.Header
import io.skysail.restlet.AbstractListResourceFilter
import io.skysail.restlet.ListResponseWrapper
import io.skysail.restlet.utils.ScalaHeadersUtils
import io.skysail.core.model.ApplicationModel
import io.skysail.core.model.LinkModel
import io.skysail.core.domain.ScalaEntity

object AddLinkheadersListFilter {
  val MAX_LINK_HEADER_SIZE = 2048
}

class AddLinkheadersListFilter[T <: List[_]](appModel: ApplicationModel) extends AbstractListResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[AddLinkheadersListFilter[T]])

  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3) = {
    val result = scala.collection.mutable.ListBuffer[LinkModel]()
    val resourceModel = appModel.resourceModelFor(resource.getClass).get
    val listEntities = resource.getEntity().asInstanceOf[List[ScalaEntity[_]]]
    for (link <- appModel.linksFor(resource.getClass)) {
      val pathVariables = getPathVariables(link.getUri())
      for (listEntity <- listEntities) {
        val substituedUrl = pathVariables.foldLeft(link.path)((seed,variable) => seed.replace("{"+variable+"}",listEntity.getId().toString()))
        result += link.copy(path = substituedUrl)
      }
    }
    val links = result.toList ::: resource.runtimeLinks()
   	val limitedLinks = links.map(l => l.asLinkheaderElement()).mkString(",")
    val responseHeaders = ScalaHeadersUtils.getHeaders(resource.getResponse())
    responseHeaders.add(new Header("Link", limitedLinks));
  }

   private def getPathVariables(path: String) = 
    "\\{([^\\}]*)\\}".r
      .findAllIn(path)
      .map { (e => e.toString().replace("{", "").replace("}", "")) }
      .toList
}