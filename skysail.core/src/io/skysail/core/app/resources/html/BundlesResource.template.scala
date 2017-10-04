
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main
import html.head

object BundlesResource extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(msg: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.15*/("""

"""),_display_(/*8.2*/main/*8.6*/ {_display_(Seq[Any](format.raw/*8.8*/("""
 """),format.raw/*9.2*/("""this is main
 <h1>"""),_display_(/*10.7*/msg),format.raw/*10.10*/("""</h1>
""")))}))
      }
    }
  }

  def render(msg:String): play.twirl.api.HtmlFormat.Appendable = apply(msg)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (msg) => apply(msg)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Oct 04 08:13:26 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource.scala.html
                  HASH: 26098dbae2ad07ec10a84e4143b2941710bc931a
                  MATRIX: 607->193|715->206|743->209|754->213|792->215|820->217|865->236|889->239
                  LINES: 15->6|20->6|22->8|22->8|22->8|23->9|24->10|24->10
                  -- GENERATED --
              */
          