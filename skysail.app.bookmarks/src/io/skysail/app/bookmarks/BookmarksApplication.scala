package io.skysail.app.bookmarks

import akka.http.scaladsl.server.{PathMatcher, PathMatchers}
import io.skysail.api.persistence.DbService
import io.skysail.app.bookmarks.BookmarksApplication._
import io.skysail.core.app._
import io.skysail.core.app.menus.MenuItem
import io.skysail.core.app.resources.BundleResource
import java.io

object BookmarksApplication {
  val APPLICATION_NAME = "bookmarks"
  val API_VERSION = ApiVersion(1)
}

class BookmarksApplication extends SkysailApplication(APPLICATION_NAME, API_VERSION, "Skysail Bookmark Application") with ApplicationProvider {

  var dbService: DbService = _
  var repo: BookmarksRepository = _

  def activate(): Unit = repo = new BookmarksRepository(dbService)
  def deactivate(): Unit = repo = null

  override def menu(): Some[MenuItem] = {
    Some(
      MenuItem("Bookmarks", "fa-file-o", None, Some(List(
        MenuItem("Overview", "fa-plus", Some("/client/bookmarks/v1")),
        MenuItem("---", "fa-user", None, Some(List(
          MenuItem("add ---", "fa-plus", Some("/client/demo/v1/contacts/new"))
        )))
      ))))
  }

  override def routesMappings: List[RouteMapping[_]] = {
    val root = PathMatcher("bookmarks") / PathMatcher("v1")
    List(
      RouteMapping(null, classOf[BookmarksResource]).setPathMatcher(PathMatcher("bookmarks") / PathMatcher("v1")),
      //RouteMapping("", classOf[BookmarksResource]),
      RouteMapping("/bm", classOf[BookmarksResource]),
      RouteMapping("/bm/new", classOf[PostBookmarkResource]), // fix me
      //RouteMapping("/bm/:id", classOf[BookmarkResource]),
      RouteMapping(null, classOf[PutBookmarkResource]).setPathMatcher(root / PathMatcher("bm") / PathMatchers.Segment ~ PathMatchers.Slash)
      //RouteMapping("/bm/:id/", classOf[PutBookmarkResource])
    )
  }

}