
package io.skysail.app.bookmarks.html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

object BookmarksResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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

"""),format.raw/*10.1*/("""<br><br><br>

<div class="container">
    <div class="starter-template">
        <h1>Bookmarks</h1>
        <p class="lead">all bookmarks:</p>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Url</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*26.14*/for(p <- rep.rawData) yield /*26.35*/ {_display_(Seq[Any](format.raw/*26.37*/("""
            """),format.raw/*27.13*/("""<tr>
                <th scope="row">"""),_display_(/*28.34*/p/*28.35*/.get("id")),format.raw/*28.45*/("""</th>
                <th scope="row">"""),_display_(/*29.34*/p/*29.35*/.get("title")),format.raw/*29.48*/("""</th>
                <td><a href='"""),_display_(/*30.31*/p/*30.32*/.get("url")),format.raw/*30.43*/("""'>"""),_display_(/*30.46*/p/*30.47*/.get("url")),format.raw/*30.58*/("""</a></td>
                <td><a href='/bookmarks/v1/bm/"""),_display_(/*31.48*/p/*31.49*/.get("id")),format.raw/*31.59*/("""'>[update]</a></td>
            </tr>
            """)))}),format.raw/*33.14*/("""

            """),format.raw/*35.13*/("""</tbody>

        </table>

        <a href="/bookmarks/v1/bm/">New</a>

    </div>
</div>

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
                  DATE: Thu Oct 12 16:14:07 CEST 2017
                  SOURCE: C:/git/skysail-core/skysail.app.bookmarks/./resources/templates/io/skysail/app/bookmarks/BookmarksResource_Get.scala.html
                  HASH: abf8d2204a6356752127230476d1876e6208ec00
                  MATRIX: 653->193|774->219|802->222|813->226|851->228|880->230|1319->642|1356->663|1396->665|1437->678|1502->716|1512->717|1543->727|1609->766|1619->767|1653->780|1716->816|1726->817|1758->828|1788->831|1798->832|1830->843|1914->900|1924->901|1955->911|2037->962|2079->976
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|40->26|40->26|40->26|41->27|42->28|42->28|42->28|43->29|43->29|43->29|44->30|44->30|44->30|44->30|44->30|44->30|45->31|45->31|45->31|47->33|49->35
                  -- GENERATED --
              */
          