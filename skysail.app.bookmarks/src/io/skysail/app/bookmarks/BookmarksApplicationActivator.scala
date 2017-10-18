package io.skysail.app.bookmarks

import domino.DominoActivator
import io.skysail.api.persistence.DbService
import io.skysail.core.app.ApplicationProvider

class BookmarksApplicationActivator  extends DominoActivator{

  whenBundleActive {
    whenServicePresent[DbService] { s =>
      val app = new BookmarksApplication()
      app.dbService = s
      app.providesService[ApplicationProvider]
    }
  }
}
