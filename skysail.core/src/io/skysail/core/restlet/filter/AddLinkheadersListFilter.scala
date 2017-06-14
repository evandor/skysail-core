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

object AddLinkheadersListFilter {
  val MAX_LINK_HEADER_SIZE = 2048
}

class AddLinkheadersListFilter[T <: List[_]](appModel: ApplicationModel) extends AbstractListResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[AddLinkheadersListFilter[T]])

  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3) = {
    val result = scala.collection.mutable.ListBuffer[LinkModel]()
    for (link <- appModel.linksFor(resource.getClass)) {
      if (link.getUri().contains("{")) {
        result += link
      } else {
        result += link
      }
    }
    
    val links = appModel.linksFor(resource.getClass) ::: resource.runtimeLinks()
    for (link <- links) {
      
    }

    //    linkheaderAuthorized.forEach(getPathSubstitutions(resource));
    val linkCount = 50;
   // val limitedLinks = shrinkLinkHeaderSizeIfNecessary(linkCount, links.map(l => l.asLinkheaderElement()).mkString(","))
   	val limitedLinks = links.map(l => l.asLinkheaderElement()).mkString(",")
    //    if (limitedLinks.length() < links.length()) {
    //      responseHeaders.add(new Header("X-Link-Error", "link header was too large: " + links.length() + " bytes, cutting down to " + limitedLinks.length() + " bytes."));
    //    }
    val responseHeaders = ScalaHeadersUtils.getHeaders(resource.getResponse())
    responseHeaders.add(new Header("Link", limitedLinks));
  }

 /* private def shrinkLinkHeaderSizeIfNecessary(linkCount: Int, links: String): String = {
    if (linkCount <= 0) {
      return ""
    }
    if (links.length() > AddLinkheadersListFilter.MAX_LINK_HEADER_SIZE) {
      val reducedLinks = Arrays.stream(links.split(",", linkCount)).limit(linkCount - 1)
        .collect(Collectors.joining(",")); // NOSONAR
      return shrinkLinkHeaderSizeIfNecessary(linkCount - 10, reducedLinks);
    }
    return links;
  }*/

}