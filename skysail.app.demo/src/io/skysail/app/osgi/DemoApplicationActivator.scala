package io.skysail.app.osgi

import domino.DominoActivator
import io.skysail.api.persistence.DbService
import io.skysail.core.app.ApplicationProvider

class DemoApplicationActivator  extends DominoActivator{

  whenBundleActive {
    whenServicePresent[DbService] { s =>
      val app = new DemoApplication()
      app.dbService = s
      app.providesService[ApplicationProvider]
    }
  }
}
