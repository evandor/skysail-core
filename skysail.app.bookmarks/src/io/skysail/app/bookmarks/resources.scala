package io.skysail.app.bookmarks

import io.skysail.core.akka.{RequestEvent, ResponseEvent}
import io.skysail.core.resources.{AsyncEntityResource, AsyncListResource}

class BookmarksResource extends AsyncListResource[Bookmark] {
  val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
    val r = appService.getApplications().toList
    requestEvent.resourceActor ! ResponseEvent(requestEvent, r)
  }
}

class AppResource extends AsyncEntityResource[Bookmark] {
  val appService = new ApplicationService()

  def get(requestEvent: RequestEvent): Unit = {
    Bookmark("hi", "http://www.heise.de")
  }
}