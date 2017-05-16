package io.skysail.core.app.resources

import io.skysail.restlet.resources._
import io.skysail.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication
import io.skysail.restlet.menu.MenuItem

class DefaultResource extends ListServerResource[MenuItemDescriptor] {

  override def runtimeLinks() = {
    val appService = getSkysailApplication().getSkysailApplicationService()
    appService.getApplicationContextResources().map{_.linkModel}.toList
  }
  
  //    private Link createLinkForApp(MenuItem mi) {
  //        Predicate<String[]> securedBy = null;
  //        return new Link.Builder(mi.getLink()).relation(LinkRelation.ITEM).title(mi.getName()).role(LinkRole.MENU_ITEM)
  //                .authenticationNeeded(true).needsRoles(securedBy).build();
  //    }

  override def redirectTo(): String = {
    getSkysailApplication().asInstanceOf[SkysailRootApplication].getRedirectTo(this);
  }

  override def getEntity() = {
    //        Set<MenuItem> mainMenuItems = ((SkysailRootApplication)getApplication()).getMainMenuItems(this,getRequest());
    //        return mainMenuItems.stream()
    //                .map(i -> new MenuItemDescriptor(i))
    //                .sorted((m1,m2) -> m1.getName().compareTo(m2.getName()))
    //                .collect(Collectors.toList());
    val appService = getSkysailApplication().getSkysailApplicationService()
    val linkModels = appService.getApplicationContextResources().map{_.linkModel}.toList
    linkModels.map { linkModel => new MenuItemDescriptor(new MenuItem(linkModel.getTitle(), linkModel.getUri())) }
  }
}