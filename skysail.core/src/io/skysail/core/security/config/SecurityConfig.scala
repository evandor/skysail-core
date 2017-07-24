//package io.skysail.core.security.config
//
//import io.skysail.api.um.AuthenticationService
//import org.restlet.Context
//import org.restlet.security.Authenticator
//import org.slf4j.LoggerFactory
//import io.skysail.api.um.NeverAuthenticatedAuthenticator
//
//class SecurityConfig(authenticationService: AuthenticationService) {
//
//  var log = LoggerFactory.getLogger(this.getClass())
//
//  val matchers = scala.collection.mutable.ListBuffer[PathToAuthenticatorMatcher]()
//
//  def authenticatorFor(context: Context, path: String) = {
//    matchers
//      .filter(matcher => matcher.`match`(path))
//      .map(matcher => matcher.getAuthenticator(context, authenticationService))
//      .filter(_ != null)
//      .headOption
//      .getOrElse(new NeverAuthenticatedAuthenticator(context))
//  }
//
//  def addPathToAuthenticatorMatcher(pathToAuthenticatorMatcher: PathToAuthenticatorMatcher) = {
//    matchers += pathToAuthenticatorMatcher
//  }
//
//}