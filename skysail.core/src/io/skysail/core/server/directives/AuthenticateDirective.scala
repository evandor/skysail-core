package io.skysail.core.server.directives

import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.server._

trait MyDirectives {

  def test(): Directive0 = Directive.Empty

  def tunnelMethods(ctx: RequestContext, optionalTunnelMethod: Option[String]): Directive0 = {
    new TunnelMethodDirective(ctx, optionalTunnelMethod)
  }

  def test1(s: String): AuthenticateDirective = {
    //println(s)
    AuthenticateDirective
  }

  //def myauth(s: String): Directive1[String] = AuthenticateDirective

}

object MyDirectives extends MyDirectives {

}

trait AuthenticateDirective extends Directive1[String] {

  //def myauth(s:String):Directive1[String] = AuthenticateDirective.this

  override def tapply(f: (Tuple1[String]) => Route): Route = {
    //println("xxx")
    val t = Tuple1("ttt")
    f(t)
  }
}

object AuthenticateDirective extends AuthenticateDirective {

}

class TunnelMethodDirective(ctx: RequestContext, optionalTunnelMethod: Option[String]) extends Directive0 {

  override def tapply(f: Unit => Route) = ???
}
