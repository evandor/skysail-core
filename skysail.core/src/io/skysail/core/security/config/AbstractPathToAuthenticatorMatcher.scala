//package io.skysail.core.security.config
//
//import io.skysail.api.um.AuthenticationMode
//import io.skysail.api.um.AuthenticationService
//
//abstract class AbstractPathToAuthenticatorMatcher(securityConfigBuilder: SecurityConfigBuilder) extends PathToAuthenticatorMatcher {
//
//  var authMode: AuthenticationMode = null
//
//  override def permitAll() = {
//    authMode = AuthenticationMode.PERMIT_ALL
//    securityConfigBuilder.getPathToAuthenticatorMatcherRegistry()
//  }
//
//  override def anonymous() = {
//    authMode = AuthenticationMode.ANONYMOUS;
//    securityConfigBuilder.getPathToAuthenticatorMatcherRegistry();
//  }
//
//  override def authenticated() = {
//    authMode = AuthenticationMode.AUTHENTICATED;
//    securityConfigBuilder.getPathToAuthenticatorMatcherRegistry();
//  }
//
//  override def denyAll() = {
//    authMode = AuthenticationMode.DENY_ALL;
//    securityConfigBuilder.getPathToAuthenticatorMatcherRegistry();
//  }
//
//  override def getAuthenticator(context: Context, authenticationService: AuthenticationService) = {
//     authenticationService.getResourceAuthenticator(context, authMode);
//  }
//
//}