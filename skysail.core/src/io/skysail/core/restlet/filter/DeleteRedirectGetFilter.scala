package io.skysail.core.restlet.filter

import io.skysail.core.restlet.AbstractResourceFilter
import org.slf4j.LoggerFactory
import io.skysail.core.restlet.SkysailServerResource
import org.restlet.data.MediaType
import org.restlet.representation.Variant
import io.skysail.core.restlet.Wrapper3

class DeleteRedirectGetFilter2[T: Manifest](variant: Variant) extends AbstractResourceFilter[T] {

  override val log = LoggerFactory.getLogger(classOf[PutRedirectGetFilter[T]])

  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3): Unit = {
    if (resource.getQuery() == null) {
      return
    }
    var redirectTo = resource.redirectTo();
    val noRedirects = resource.getQuery().getFirst(SkysailServerResource.NO_REDIRECTS);
    if (redirectTo != null && noRedirects == null) {
      //val variant = resource.getRequest().getAttributes().get(SkysailServerResource.SKYSAIL_SERVER_RESTLET_VARIANT).asInstanceOf[Variant]
      if (MediaType.TEXT_HTML.equals(variant.getMediaType())) {
        //redirectTo = augmentWithMessageIds(redirectTo, responseWrapper.getMessageIds());
        resource.getResponse().redirectSeeOther(redirectTo);
      }
    }
  }

  private def augmentWithMessageIds(redirectTo: String, messageIds: List[Long]): String = {
    if (messageIds.isEmpty) {
      return redirectTo;
    }
    var result: String = ""
    if (redirectTo.contains("?")) {
      result = redirectTo + "&";
    } else {
      result = redirectTo + "?";
    }
    return result + "msgIds=" + messageIds.map(id => id.toString()).mkString("|")
  }

}