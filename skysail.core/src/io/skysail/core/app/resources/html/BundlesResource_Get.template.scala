
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
        <th>Aktionen</th>
    </tr>
    </thead>
    <tbody>
    """),_display_(/*24.6*/for(p <- rep.rawData) yield /*24.27*/ {_display_(Seq[Any](format.raw/*24.29*/("""
    """),format.raw/*25.5*/("""<tr>
        <th scope="row"><a href='"""),_display_(/*26.35*/rep/*26.38*/.linkFor("io.skysail.core.app.resources.BundleResource", p.get("id"))),format.raw/*26.107*/("""'>"""),_display_(/*26.110*/p/*26.111*/.get("id")),format.raw/*26.121*/("""</a></th>
        <td>"""),_display_(/*27.14*/p/*27.15*/.get("symbolicName")),format.raw/*27.35*/("""</td>
        <td>"""),_display_(/*28.14*/p/*28.15*/.get("registeredServiceIds").size),format.raw/*28.48*/("""</td>
        <td>"""),_display_(/*29.14*/p/*29.15*/.get("servicesInUse").size),format.raw/*29.41*/("""</td>
        <th>"""),_display_(/*30.14*/p/*30.15*/.get("version")),format.raw/*30.30*/("""</th>
        <th>"""),_display_(/*31.14*/p/*31.15*/.get("state")),format.raw/*31.28*/("""</th>
        <th>"""),_display_(/*32.14*/p/*32.15*/.get("size")),format.raw/*32.27*/("""</th>
        <th>[<a href='"""),_display_(/*33.24*/rep/*33.27*/.linkFor("io.skysail.core.app.resources.StartBundleResource", p.get("id"))),format.raw/*33.101*/("""'>start</a>][<a href='"""),_display_(/*33.124*/rep/*33.127*/.linkFor("io.skysail.core.app.resources.StopBundleResource", p.get("id"))),format.raw/*33.200*/("""'>stop</a>]</th>
    </tr>
    """)))}),format.raw/*35.6*/("""
    """),format.raw/*36.5*/("""</tbody>

    """),format.raw/*48.10*/("""
"""),format.raw/*49.1*/("""</table>
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
                  DATE: Wed Oct 18 08:10:01 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource_Get.scala.html
                  HASH: 868e0c965e01c836ce238099e2566bf7262df018
                  MATRIX: 656->193|777->219|805->222|816->226|854->228|883->230|1208->529|1245->550|1285->552|1317->557|1383->596|1395->599|1486->668|1517->671|1528->672|1560->682|1610->705|1620->706|1661->726|1707->745|1717->746|1771->779|1817->798|1827->799|1874->825|1920->844|1930->845|1966->860|2012->879|2022->880|2056->893|2102->912|2112->913|2145->925|2201->954|2213->957|2309->1031|2360->1054|2373->1057|2468->1130|2530->1162|2562->1167|2604->1358|2632->1359
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|38->24|38->24|38->24|39->25|40->26|40->26|40->26|40->26|40->26|40->26|41->27|41->27|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|45->31|45->31|45->31|46->32|46->32|46->32|47->33|47->33|47->33|47->33|47->33|47->33|49->35|50->36|52->48|53->49
                  -- GENERATED --
              */
          