
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

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

"""),format.raw/*10.1*/("""<table class="table table-sm">
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
    """),_display_(/*23.6*/for(p <- rep.rawData) yield /*23.27*/ {_display_(Seq[Any](format.raw/*23.29*/("""
    """),format.raw/*24.5*/("""<tr>
        <th scope="row">"""),_display_(/*25.26*/p/*25.27*/.get("id")),format.raw/*25.37*/("""</th>
        <td>"""),_display_(/*26.14*/p/*26.15*/.get("symbolicName")),format.raw/*26.35*/("""</td>
        <td>"""),_display_(/*27.14*/p/*27.15*/.get("registeredServiceIds").size),format.raw/*27.48*/("""</td>
        <td>"""),_display_(/*28.14*/p/*28.15*/.get("servicesInUse").size),format.raw/*28.41*/("""</td>
        <th>"""),_display_(/*29.14*/p/*29.15*/.get("version")),format.raw/*29.30*/("""</th>
        <th>"""),_display_(/*30.14*/p/*30.15*/.get("state")),format.raw/*30.28*/("""</th>
        <th>"""),_display_(/*31.14*/p/*31.15*/.get("size")),format.raw/*31.27*/("""</th>
    </tr>
    """)))}),format.raw/*33.6*/("""
    """),format.raw/*34.5*/("""</tbody>

    """),format.raw/*46.10*/("""
"""),format.raw/*47.1*/("""</table>
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
                  DATE: Thu Oct 05 20:14:35 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource.scala.html
                  HASH: bb3886be977affc79ddaaec96b20284e09175cb5
                  MATRIX: 652->193|773->219|801->222|812->226|850->228|879->230|1178->503|1215->524|1255->526|1287->531|1344->561|1354->562|1385->572|1431->591|1441->592|1482->612|1528->631|1538->632|1592->665|1638->684|1648->685|1695->711|1741->730|1751->731|1787->746|1833->765|1843->766|1877->779|1923->798|1933->799|1966->811|2017->832|2049->837|2091->1028|2119->1029
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|37->23|37->23|37->23|38->24|39->25|39->25|39->25|40->26|40->26|40->26|41->27|41->27|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|45->31|45->31|45->31|47->33|48->34|50->46|51->47
                  -- GENERATED --
              */
          