
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
        <th scope="row"><a href='"""),_display_(/*26.35*/rep/*26.38*/.linkFor("BundleResource", p.get("id"))),format.raw/*26.77*/("""'>"""),_display_(/*26.80*/p/*26.81*/.get("id")),format.raw/*26.91*/("""</a></th>
        <td>"""),_display_(/*27.14*/p/*27.15*/.get("symbolicName")),format.raw/*27.35*/("""</td>
        <td>"""),_display_(/*28.14*/p/*28.15*/.get("registeredServiceIds").size),format.raw/*28.48*/("""</td>
        <td>"""),_display_(/*29.14*/p/*29.15*/.get("servicesInUse").size),format.raw/*29.41*/("""</td>
        <th>"""),_display_(/*30.14*/p/*30.15*/.get("version")),format.raw/*30.30*/("""</th>
        <th>"""),_display_(/*31.14*/p/*31.15*/.get("state")),format.raw/*31.28*/("""</th>
        <th>"""),_display_(/*32.14*/p/*32.15*/.get("size")),format.raw/*32.27*/("""</th>
        <th>[<a href='"""),_display_(/*33.24*/rep/*33.27*/.linkFor("BundleResource", p.get("id"))),format.raw/*33.66*/("""/start'>start</a>][<a href='"""),_display_(/*33.95*/rep/*33.98*/.linkFor("BundleResource", p.get("id"))),format.raw/*33.137*/("""/stop'>stop</a>]</th>
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
                  DATE: Tue Oct 17 07:37:06 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource_Get.scala.html
                  HASH: 1ff74bbabfd10253f24a6687e3ff9a17647e5c8c
                  MATRIX: 656->193|777->219|805->222|816->226|854->228|883->230|1208->529|1245->550|1285->552|1317->557|1383->596|1395->599|1455->638|1485->641|1495->642|1526->652|1576->675|1586->676|1627->696|1673->715|1683->716|1737->749|1783->768|1793->769|1840->795|1886->814|1896->815|1932->830|1978->849|1988->850|2022->863|2068->882|2078->883|2111->895|2167->924|2179->927|2239->966|2295->995|2307->998|2368->1037|2435->1074|2467->1079|2509->1270|2537->1271
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|38->24|38->24|38->24|39->25|40->26|40->26|40->26|40->26|40->26|40->26|41->27|41->27|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|45->31|45->31|45->31|46->32|46->32|46->32|47->33|47->33|47->33|47->33|47->33|47->33|49->35|50->36|52->48|53->49
                  -- GENERATED --
              */
          