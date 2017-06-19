package io.skysail.core.restlet.menu;

import io.skysail.core.domain.ScalaEntity;
import io.skysail.core.html.Field;

public class MenuItemDescriptorJava { //implements ScalaEntity<String> {

    @Field
    //@ListView
    private String url;

    private String name;

    public MenuItemDescriptorJava(MenuItem menuItem) {
//        name = menuItem.get;
//        String link = menuItem.getLink();
//        url = "<a href='"+link+"'>"+name+"</a>";
    }

   // @Override
    public String getId() {
        return url;
    }


}
