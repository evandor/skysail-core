
package html

import play.twirl.api.Html

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
                  DATE: Wed Oct 04 17:34:27 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/main.scala.html
                  HASH: 3ce52d48a544fc818d359f4510453ae91dbe7339
                  MATRIX: 343->1|453->16|480->17|574->86|600->92|627->93|676->116|703->123|731->124
                  LINES: 9->1|14->1|15->2|19->6|19->6|20->7|24->11|24->11|25->12
                  -- GENERATED --
              */
          