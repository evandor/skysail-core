package io.skysail.api.security

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive1, PathMatcher, RequestContext, Route }

trait AuthenticationService {
  def directive(): Directive1[String]
}