
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main

object BundlesResource extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.28*/("""

"""),_display_(/*8.2*/main/*8.6*/ {_display_(Seq[Any](format.raw/*8.8*/("""
"""),format.raw/*9.1*/("""this is main
<h1>"""),_display_(/*10.6*/rep),format.raw/*10.9*/("""</h1>


<table class="table table-sm">
    <thead>
    <tr>
        <th>#</th>
        <th>Symbolic Name</th>
        <th># prov. Serv.</th>
        <th># used Serv.</th>
        <th>Version</th>
        <th>State</th>
        <th>Size</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>Otto</td>
        <td>mdo</td>
        <th># used Serv.</th>
        <th>Version</th>
        <th>Version</th>
    </tr>
    </tbody>
</table>
""")))}))
      }
    }
  }

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Oct 05 09:12:21 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource.scala.html
                  HASH: 17bbc10647eab0ffd51cacfb22b0d11d922ea45f
                  MATRIX: 603->193|724->219|752->222|763->226|801->228|828->229|872->247|895->250
                  LINES: 14->6|19->6|21->8|21->8|21->8|22->9|23->10|23->10
                  -- GENERATED --
              */
          