//package io.skysail.core.restlet.filter
//
//import org.slf4j.LoggerFactory
//import io.skysail.core.restlet.AbstractResourceFilter
//import io.skysail.core.restlet.SkysailServerResource
//import org.restlet.data.Header
//import io.skysail.core.restlet.utils.ScalaHeadersUtils
//import io.skysail.core.restlet.Wrapper3
//
//object AddLinkheadersFilter {
//  val MAX_LINK_HEADER_SIZE = 2048
//}
//
//class AddLinkheadersFilter[T:Manifest] extends AbstractResourceFilter[T] {
//
//  override val log = LoggerFactory.getLogger(classOf[AddLinkheadersFilter[T]])
//
//  override def afterHandle(resource: SkysailServerResource[_], responseWrapper: Wrapper3) = {
//    val responseHeaders = ScalaHeadersUtils.getHeaders(resource.getResponse());
//    //val linkheaderAuthorized = resource.getAuthorizedLinks();
////    linkheaderAuthorized.forEach(getPathSubstitutions(resource));
////    val links = linkheaderAuthorized.stream().map(link -> link.toString(""))
////      .collect(Collectors.joining(","));
//    val linkCount = 50;
//    val limitedLinks = "50" //shrinkLinkHeaderSizeIfNecessary(linkCount, links);
//    //    if (limitedLinks.length() < links.length()) {
//    //      responseHeaders.add(new Header("X-Link-Error", "link header was too large: " + links.length() + " bytes, cutting down to " + limitedLinks.length() + " bytes."));
//    //    }
//    responseHeaders.add(new Header("Link", limitedLinks));
//  }
//
//}