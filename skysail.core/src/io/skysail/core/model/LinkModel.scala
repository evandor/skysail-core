package io.skysail.core.model

import org.restlet.data.Method
import io.skysail.restlet.SkysailServerResource
import scala.annotation.meta.field
import scala.beans.BeanProperty
import io.skysail.restlet.ResourceContextId

object LinkModel {
  def fromLinkheader(resource: SkysailServerResource[_], l: String): LinkModel = {
     require(l != null, "the linkheader string must not be empty")
     
     val parts = l.split(";")
     val uriPart = parts(0).trim()
     val substring = uriPart.substring(1).substring(0, uriPart.length() - 2);
//
//        Builder builder = new Link.Builder(substring);
//        for (int i = 1; i < parts.length; i++) {
//            parsePart(builder, parts[i]);
//        }
//        return builder.build();
     new LinkModel(context = "", path = uriPart, resource = resource)
  }
}

case class LinkModel(
    val context: String,
    val path: String,
    @BeanProperty rat: ResourceAssociationType = null,
    resource: SkysailServerResource[_] = null,
    @BeanProperty val resourceClass: Class[_ <: SkysailServerResource[_]] = null) {

  @BeanProperty val relation: LinkRelation = if (resource != null) resource.getLinkRelation() else LinkRelation.ALTERNATE
  @BeanProperty val verbs = if (resource != null) resource.getVerbs() else Set(Method.GET)
  @BeanProperty var title = determineTitle()
  @BeanProperty val alt: String = "-"
  @BeanProperty val needsAuth: Boolean = false
  @BeanProperty val linkRole: LinkRole = LinkRole.DEFAULT
  @BeanProperty var refId: String = _
  @BeanProperty var cls: Class[_] = _
 
  def getUri() = context + path

  override def toString() = s"'${path}': ${resourceClass} ($rat) [title: $getTitle()]"

  def asLinkheaderElement(): String = {
    val sb = new StringBuilder().append("<").append(getUri()).append(">");
    sb.append("; rel=\"").append(relation.getName()).append("\"");
    if (getTitle() != null) {
      sb.append("; title=\"").append(getTitle()).append("\"");
    }
    if (getRefId() != null) {
      sb.append("; refId=\"").append(getRefId()).append("\"");
    }
    sb.append("; verbs=\"")
      .append(verbs.map(verb => verb.getName()).mkString(",")).append("\"");
    return sb.toString();
  }
  
  def determineTitle(): String = {
    if (resource == null) {
      return "unknown"
    }
    val title = resource.getFromContext(ResourceContextId.LINK_TITLE)
    if (title == null) "unknown" else title
  }

}