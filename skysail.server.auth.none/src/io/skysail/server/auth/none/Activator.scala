package io.skysail.server.auth.none

import domino.DominoActivator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory
import io.skysail.api.security.AuthenticationService
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive1, PathMatcher, RequestContext, Route }

class Activator extends DominoActivator {

  private var log = LoggerFactory.getLogger(this.getClass)

  whenBundleActive({

    log warn ""
    log warn s"  =================================================="
    log warn s"  |                                                |"
    log warn s"  |  Bundle 'SKYSAIL SERVER AUTH NONE' active      |"
    log warn s"  |                                                |"
    log warn s"  | ---------------------------------------------- |"
    log warn s"  |                                                |"
    log warn s"  |  no authentication or authorization available. |"
    log warn s"  |  Every user can do everything.                 |"
    log warn s"  |                                                |"
    log warn s"  =================================================="
    log warn ""

    val myService = new AuthenticationService() {
      def directive() = AuthenticateDirective
    }
    
    myService.providesService[AuthenticationService]

  })

}

trait AuthenticateDirective extends Directive1[String] {

  //def myauth(s:String):Directive1[String] = AuthenticateDirective.this

  override def tapply(f: (Tuple1[String]) => Route) = {
    val t = Tuple1("tttxxx")
    f(t)
  }
}

object AuthenticateDirective extends AuthenticateDirective {}
