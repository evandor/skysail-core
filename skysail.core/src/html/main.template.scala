
package html

import play.twirl.api.Html
import html.main
import html.head

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.17*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<meta charset="utf-8"/>
<html lang="en">
<head>
    """),_display_(/*6.6*/head()),format.raw/*6.12*/("""
"""),format.raw/*7.1*/("""</head>

<body>
body:
"""),_display_(/*11.2*/content),format.raw/*11.9*/("""
"""),format.raw/*12.1*/("""</body>
</html>"""))
      }
    }
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Oct 04 08:18:48 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/main.scala.html
                  HASH: fd253f011922632f1d0ee719db033d20893a10d8
                  MATRIX: 377->1|487->16|514->17|608->86|634->92|661->93|710->116|737->123|765->124
                  LINES: 11->1|16->1|17->2|21->6|21->6|22->7|26->11|26->11|27->12
                  -- GENERATED --
              */
          