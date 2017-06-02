package io.skysail.core.app.resources

import io.skysail.restlet.resources._
import io.skysail.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication
import io.skysail.restlet.menu.MenuItem

class DefaultResource extends ListServerResource[List[MenuItemDescriptor]] {

  override def runtimeLinks() = {
    val appService = getSkysailApplication().getSkysailApplicationService()
    appService.getApplicationContextResources().map{_.linkModel}.toList
  }
  
  override def redirectTo(): String = {
    getSkysailApplication().asInstanceOf[SkysailRootApplication].getRedirectTo(this);
  }

  override def getEntity():List[MenuItemDescriptor] = {
    val appService = getSkysailApplication().getSkysailApplicationService()
    val linkModels = appService.getApplicationContextResources().map{_.linkModel}.toList
    linkModels.map { linkModel => new MenuItemDescriptor(new MenuItem(linkModel.getTitle(), linkModel.getUri())) }.toList
  }
}