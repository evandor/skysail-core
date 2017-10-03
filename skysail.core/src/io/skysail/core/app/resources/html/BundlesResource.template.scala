
package io.skysail.core.app.resources.html


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

"""),format.raw/*8.1*/("""<!DOCTYPE html>
<meta charset="utf-8" />
<title>Bundles</title>


<h2>Bundles</h2>

<h1>"""),_display_(/*15.6*/msg),format.raw/*15.9*/("""</h1>

"""))
      }
    }
  }

  def render(msg:String): play.twirl.api.HtmlFormat.Appendable = apply(msg)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (msg) => apply(msg)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Oct 03 19:31:56 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource.scala.html
                  HASH: 337c05df55a15f18059996560669db7ce230065e
                  MATRIX: 546->193|654->206|682->208|797->297|820->300
                  LINES: 12->6|17->6|19->8|26->15|26->15
                  -- GENERATED --
              */
          