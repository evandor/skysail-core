package io.skysail.app.bookmarks

import io.skysail.app.bookmarks.BookmarksApplication._
import io.skysail.core.app._
import io.skysail.core.app.menus.MenuItem
import org.osgi.service.component.annotations._

object BookmarksApplication {
  val APPLICATION_NAME = "bookmarks"
  val API_VERSION = ApiVersion(1)
}

@Component(immediate = true, property = { Array("service.pid=template") }, service = Array(classOf[ApplicationProvider]))
class BookmarksApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail Bookmark Application") with ApplicationProvider {

  override def menu() = {
    Some(
      MenuItem("Bookmarks", "fa-file-o", None, Some(List(
        MenuItem("Overview", "fa-plus", Some("/client/bookmarks/v1")),
        MenuItem("---", "fa-user", None, Some(List(
          MenuItem("add ---", "fa-plus", Some("/client/demo/v1/contacts/new"))
        )))
      ))))
  }

  override def routesMappings = List(
    RouteMapping("", classOf[BookmarksResource])
  )

}