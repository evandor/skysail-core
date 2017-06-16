package io.skysail.core.app.resources

import io.skysail.core.restlet.resources._
import io.skysail.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication
import io.skysail.restlet.menu.MenuItem
import io.skysail.core.restlet.resources.ListServerResource

/**
 * This is the Resource mapped to "/" used by the SkysailRootApplication.
 *
 */
class DefaultResource extends ListServerResource[List[MenuItemDescriptor]] {

  override def runtimeLinks() = {
    applicationService.getApplicationContextResources().map{_.linkModel}.toList
  }
  
  override def getEntity():List[MenuItemDescriptor] = {
    runtimeLinks().map { linkModel => new MenuItemDescriptor(new MenuItem(linkModel.getTitle(), linkModel.getUri())) }.toList
  }
}