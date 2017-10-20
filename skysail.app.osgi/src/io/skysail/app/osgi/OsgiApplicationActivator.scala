package io.skysail.app.osgi

import domino.DominoActivator
import io.skysail.core.app.ApplicationProvider

class OsgiApplicationActivator  extends DominoActivator{

  whenBundleActive {
    //whenServicePresent[DbService] { s =>
      val app = new OsgiApplication()
      app.providesService[ApplicationProvider]
    //}
  }
}
