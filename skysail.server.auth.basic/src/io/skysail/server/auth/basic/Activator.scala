package io.skysail.server.auth.basic

import domino.DominoActivator
import org.slf4j.LoggerFactory
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.Credentials
import io.skysail.api.security.AuthenticationService

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  var password = "password"

  whenBundleActive({

    log warn ""
    log warn s"  =================================================="
    log warn s"  |                                                |"
    log warn s"  |  Bundle 'SKYSAIL SERVER AUTH BASIC' active     |"
    log warn s"  |                                                |"
    log warn s"  | ---------------------------------------------- |"
    log warn s"  |                                                |"
    log warn s"  |  This authentication scheme is not considered  |"
    log warn s"  |  secure! Consider using a more advanced        |"
    log warn s"  |  scheme.                                       |"
    log warn s"  |  No authorization available.                   |"
    log warn s"  |                                                |"
    log warn s"  =================================================="
    log warn ""

    val myService = new AuthenticationService() {
      def directive() = authenticateBasic(realm = "secure site", myUserPassAuthenticator)
    }.providesService[AuthenticationService]


    whenConfigurationActive("auth") { conf =>
      log info s"received configuration for 'auth.basic': not shown, contains password"
      password = conf.getOrElse("password", "password").asInstanceOf[String]
    }

  })

  private def myUserPassAuthenticator(credentials: Credentials): Option[String] =
    credentials match {
      case p@Credentials.Provided(id) if p.verify(this.password) => Some(id)
      case _ => None
    }

}