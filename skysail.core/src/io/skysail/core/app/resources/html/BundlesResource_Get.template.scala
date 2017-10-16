
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
        <th scope="row"><a href='/root/bundles/"""),_display_(/*26.49*/p/*26.50*/.get("id")),format.raw/*26.60*/("""'>"""),_display_(/*26.63*/p/*26.64*/.get("id")),format.raw/*26.74*/("""</a></th>
        <td>"""),_display_(/*27.14*/p/*27.15*/.get("symbolicName")),format.raw/*27.35*/("""</td>
        <td>"""),_display_(/*28.14*/p/*28.15*/.get("registeredServiceIds").size),format.raw/*28.48*/("""</td>
        <td>"""),_display_(/*29.14*/p/*29.15*/.get("servicesInUse").size),format.raw/*29.41*/("""</td>
        <th>"""),_display_(/*30.14*/p/*30.15*/.get("version")),format.raw/*30.30*/("""</th>
        <th>"""),_display_(/*31.14*/p/*31.15*/.get("state")),format.raw/*31.28*/("""</th>
        <th>"""),_display_(/*32.14*/p/*32.15*/.get("size")),format.raw/*32.27*/("""</th>
        <th>[<a href='/root/bundles/"""),_display_(/*33.38*/p/*33.39*/.get("id")),format.raw/*33.49*/("""/start'>start</a>][<a href='/root/bundles/"""),_display_(/*33.92*/p/*33.93*/.get("id")),format.raw/*33.103*/("""/stop'>stop</a>]</th>
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
                  DATE: Fri Oct 13 11:08:26 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/BundlesResource_Get.scala.html
                  HASH: 40abaf574fbe49ace6e692a60ca69c46e38c3f9e
                  MATRIX: 656->193|777->219|805->222|816->226|854->228|883->230|1208->529|1245->550|1285->552|1317->557|1397->610|1407->611|1438->621|1468->624|1478->625|1509->635|1559->658|1569->659|1610->679|1656->698|1666->699|1720->732|1766->751|1776->752|1823->778|1869->797|1879->798|1915->813|1961->832|1971->833|2005->846|2051->865|2061->866|2094->878|2164->921|2174->922|2205->932|2275->975|2285->976|2317->986|2384->1023|2416->1028|2458->1219|2486->1220
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|38->24|38->24|38->24|39->25|40->26|40->26|40->26|40->26|40->26|40->26|41->27|41->27|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|45->31|45->31|45->31|46->32|46->32|46->32|47->33|47->33|47->33|47->33|47->33|47->33|49->35|50->36|52->48|53->49
                  -- GENERATED --
              */
          