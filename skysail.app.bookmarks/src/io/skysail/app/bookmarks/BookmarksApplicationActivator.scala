package io.skysail.app.bookmarks

import domino.DominoActivator
import io.skysail.api.persistence.DbService
import io.skysail.core.app.ApplicationProvider
import org.slf4j.LoggerFactory

class BookmarksApplicationActivator  extends DominoActivator{

  private var log = LoggerFactory.getLogger(this.getClass)

  var app: BookmarksApplication = _

  whenBundleActive {

    onStart {
      log info s"activating ${this.getClass.getName}"
    }

    onStop {
      log info s"deactivating ${this.getClass.getName}"
      app = null
    }

    whenServicePresent[DbService] { dbService =>
      log info s"dbService available in ${this.getClass.getName}"
      app = new BookmarksApplication()
      app.dbService = dbService
      app.activate()
      app.providesService[ApplicationProvider]
    }

  }
}
