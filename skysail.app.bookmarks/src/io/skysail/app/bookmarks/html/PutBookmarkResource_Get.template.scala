
package io.skysail.app.bookmarks.html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

object PutBookmarkResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

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
        <p class="lead">update bookmark:</p>
        <span>"""),_display_(/*16.16*/rep/*16.19*/.rawData),format.raw/*16.27*/("""</span>
        <form action='"""),_display_(/*17.24*/rep/*17.27*/.linkFor("io.skysail.app.bookmarks.PutBookmarkResource", rep.rawData.head.get("id"))),format.raw/*17.111*/("""?_method=PUT' method="GET">

            <table class="table table-sm">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Url</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"><input type="text" name="title" value='"""),_display_(/*28.77*/rep/*28.80*/.rawData.head.get("title")),format.raw/*28.106*/("""'/></th>
                    <td><input type="url" name="url" value='"""),_display_(/*29.62*/rep/*29.65*/.rawData.head.get("url")),format.raw/*29.89*/("""'/></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit">
                    </th>
                </tr>
                </tbody>

            </table>
        </form>

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
                  DATE: Tue Oct 24 12:31:25 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.app.bookmarks/./resources/templates/io/skysail/app/bookmarks/PutBookmarkResource_Get.scala.html
                  HASH: 8ddc10d4f849cb06a75e6274b60b30d4f2dcb23f
                  MATRIX: 655->193|776->219|804->222|815->226|853->228|882->230|1069->390|1081->393|1110->401|1168->432|1180->435|1286->519|1666->872|1678->875|1726->901|1823->971|1835->974|1880->998
                  LINES: 15->6|20->6|22->8|22->8|22->8|24->10|30->16|30->16|30->16|31->17|31->17|31->17|42->28|42->28|42->28|43->29|43->29|43->29
                  -- GENERATED --
              */
          