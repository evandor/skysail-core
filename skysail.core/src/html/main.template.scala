
package html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

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

    """),_display_(/*11.6*/navbar()),format.raw/*11.14*/("""

    """),format.raw/*13.5*/("""<div class="container">
        <div class="starter-template">
            <h1>Bootstrap starter template</h1>
            <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
            """),_display_(/*17.14*/content),format.raw/*17.21*/("""
        """),format.raw/*18.9*/("""</div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>
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
                  DATE: Thu Oct 05 17:07:20 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/main.scala.html
                  HASH: e4f27e0d871ef95821faec5b88bc55eb65fe009d
                  MATRIX: 409->1|519->16|546->17|640->86|666->92|693->93|741->115|770->123|803->129|1114->413|1142->420|1178->429
                  LINES: 11->1|16->1|17->2|21->6|21->6|22->7|26->11|26->11|28->13|32->17|32->17|33->18
                  -- GENERATED --
              */
          