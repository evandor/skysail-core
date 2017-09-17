package io.skysail.server.persistence.orientdb

import domino.DominoActivator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory
import io.skysail.api.security.AuthenticationService
import io.skysail.api.persistence.DbService

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  whenBundleActive({
    log info s"creating new OrientDbGraphService"
    val myService = new OrientDbGraphService()
    
    log info s"providing new DbService"
    myService.providesService[DbService]

  })
  

}
