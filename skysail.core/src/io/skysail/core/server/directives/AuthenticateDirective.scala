package io.skysail.core.server.directives

import akka.http.scaladsl.server.{Directive, Directive0, Directive1, Route}

class AuthenticateDirective extends Directive1[String] {

  def myauth(s:String):Directive1[String] = AuthenticateDirective.this

  override def tapply(f: (Tuple1[String]) => Route) = {
    println ("xxx")
    f()
  }
}
