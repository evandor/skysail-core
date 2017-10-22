package io.skysail.server.persistence.orientdb

import domino.DominoActivator
import io.skysail.api.persistence.DbService
import org.slf4j.LoggerFactory

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  whenBundleActive({
    log info s"OrientDbGraphService Bundle active, waiting for configuration..."

    whenConfigurationActive("db") { conf =>
      log info s"received configuration for OrientDbGraphService Bundle, applying..."
      val url = conf.getOrElse("url", "memory:testdb").toString
      val user = conf.getOrElse("user", "admin").toString
      val pass = conf.getOrElse("pass", "admin").toString

      log info s"url set to ${url}"
      log info s"user set to ${user}"
      log info s"pass set to ******"

      val myService = new OrientDbGraphService(url, user, pass)

      log info s"providing new DbService"
      myService.providesService[DbService]

    }


  })
  

}
