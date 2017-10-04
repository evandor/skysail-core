
package io.skysail.core.app.resources.html

import html.main
import play.twirl.api.Html

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
                  DATE: Wed Oct 04 17:34:27 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource.scala.html
                  HASH: ac6ea7a25d462f12d449a60f16f886e41498db8f
                  MATRIX: 573->193|681->206|709->209|720->213|758->215|786->217|831->236|855->239
                  LINES: 13->6|18->6|20->8|20->8|20->8|21->9|22->10|22->10
                  -- GENERATED --
              */
          