package io.skysail.server.auth.basic

import domino.DominoActivator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory
import io.skysail.api.security.AuthenticationService
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive1, PathMatcher, RequestContext, Route }
import akka.http.scaladsl.server.directives.Credentials

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

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

  })

  private def myUserPassAuthenticator(credentials: Credentials): Option[String] =
    credentials match {
      case p @ Credentials.Provided(id) if p.verify("p4ssw0rd") => Some(id)
      case _ => None
    }

}