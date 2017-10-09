
package io.skysail.app.bookmarks.html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

object BookmarksResource extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
                <th>Title</th>
                <th>Url</th>
            </tr>
            </thead>
            <tbody>
            """),_display_(/*24.14*/for(p <- rep.rawData) yield /*24.35*/ {_display_(Seq[Any](format.raw/*24.37*/("""
            """),format.raw/*25.13*/("""<tr>
                <th scope="row">"""),_display_(/*26.34*/p/*26.35*/.get("title")),format.raw/*26.48*/("""</th>
                <td><a href='"""),_display_(/*27.31*/p/*27.32*/.get("url")),format.raw/*27.43*/("""'>"""),_display_(/*27.46*/p/*27.47*/.get("url")),format.raw/*27.58*/("""</a></td>
            </tr>
            """)))}),format.raw/*29.14*/("""
            """),format.raw/*30.13*/("""<form action="/bookmarks/v1/" method="post">
                <tr>
                    <th scope="row"><input type="text" name="title"/></th>
                    <td><input type="url" name="url"/></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit">
                    </th>
                </tr>
            </form>
            </tbody>

        </table>

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
                  DATE: Sat Oct 07 19:32:46 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.app.bookmarks/./resources/templates/io/skysail/app/bookmarks/BookmarksResource_Get.scala.html
                  HASH: 4f9b61f2e62677efd4efba9fb16be5dfec805601
                  MATRIX: 649->193|770->219|798->222|809->226|847->228|876->230|1254->581|1291->602|1331->604|1372->617|1437->655|1447->656|1481->669|1544->705|1554->706|1586->717|1616->720|1626->721|1658->732|1730->773|1771->786
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|38->24|38->24|38->24|39->25|40->26|40->26|40->26|41->27|41->27|41->27|41->27|41->27|41->27|43->29|44->30
                  -- GENERATED --
              */
          