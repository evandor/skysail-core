package io.skysail.core.server.directives

import akka.http.scaladsl.server.{ Directive, Directive0, Directive1, Route }
import akka.http.scaladsl.server.RequestContext

//object AuthenticateDirective {
//  
//}

trait MyDirectives {

  def test():Directive0 = Directive.Empty
  
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

  override def tapply(f: (Tuple1[String]) => Route) = {
    //println("xxx")
    val t = Tuple1("ttt")
    f(t)
  }
}

object AuthenticateDirective extends AuthenticateDirective {
  
}
