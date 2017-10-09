
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

object BundlesResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
                  DATE: Sun Oct 08 09:27:44 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource_Get.scala.html
                  HASH: bb3886be977affc79ddaaec96b20284e09175cb5
                  MATRIX: 656->193|777->219|805->222|816->226|854->228|883->230|1182->503|1219->524|1259->526|1291->531|1348->561|1358->562|1389->572|1435->591|1445->592|1486->612|1532->631|1542->632|1596->665|1642->684|1652->685|1699->711|1745->730|1755->731|1791->746|1837->765|1847->766|1881->779|1927->798|1937->799|1970->811|2021->832|2053->837|2095->1028|2123->1029
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|37->23|37->23|37->23|38->24|39->25|39->25|39->25|40->26|40->26|40->26|41->27|41->27|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|45->31|45->31|45->31|47->33|48->34|50->46|51->47
                  -- GENERATED --
              */
          