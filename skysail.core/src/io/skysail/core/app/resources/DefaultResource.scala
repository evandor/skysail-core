package io.skysail.core.app.resources

import io.skysail.restlet.resources._
import io.skysail.restlet.menu.MenuItemDescriptor
import io.skysail.core.app.SkysailRootApplication

class DefaultResource extends ListServerResource[MenuItemDescriptor] {

  override def linkedResourceClasses() = {
    val appService = getSkysailApplication().getSkysailApplicationService()
    appService.getApplicationContextResources().map{_.targetResourceClass}.toList
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
    List()
  }
}