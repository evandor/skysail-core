package io.skysail.core.app.resources

import io.skysail.core.restlet.resources._
import io.skysail.core.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication
import io.skysail.core.restlet.menu.MenuItem
import io.skysail.core.restlet.resources.ListServerResource
import io.skysail.core.akka.ResourceDefinition

class DefaultResource2 extends ResourceDefinition {

//  override def runtimeLinks() = {
//    applicationService.getApplicationContextResources().map { _.linkModel }.toList
//  }
//
//  override def getEntity(): List[MenuItemDescriptor] = {
//    runtimeLinks().map { linkModel => new MenuItemDescriptor(new MenuItem(linkModel.getTitle(), linkModel.getUri())) }.toList
//  }
//
//  override def redirectTo() = {
//    getSkysailApplication().asInstanceOf[SkysailRootApplication].getRedirectTo(this);
//  }
}