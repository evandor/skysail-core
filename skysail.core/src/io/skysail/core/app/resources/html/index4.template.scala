
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main
import io.skysail.core.model.RepresentationModel

object index4 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<meta charset="utf-8" />
<title>WebSocket Test</title>
<script language="javascript" type="text/javascript">

  //var wsUri = "ws://echo.websocket.org/";
  var wsUri = "ws://localhost:8080/websocket"
  var output;

  function init()
  """),format.raw/*11.3*/("""{"""),format.raw/*11.4*/("""
    """),format.raw/*12.5*/("""output = document.getElementById("output");
    testWebSocket();
  """),format.raw/*14.3*/("""}"""),format.raw/*14.4*/("""

  """),format.raw/*16.3*/("""function testWebSocket()
  """),format.raw/*17.3*/("""{"""),format.raw/*17.4*/("""
    """),format.raw/*18.5*/("""websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) """),format.raw/*19.38*/("""{"""),format.raw/*19.39*/(""" """),format.raw/*19.40*/("""onOpen(evt) """),format.raw/*19.52*/("""}"""),format.raw/*19.53*/(""";
    websocket.onclose = function(evt) """),format.raw/*20.39*/("""{"""),format.raw/*20.40*/(""" """),format.raw/*20.41*/("""onClose(evt) """),format.raw/*20.54*/("""}"""),format.raw/*20.55*/(""";
    websocket.onmessage = function(evt) """),format.raw/*21.41*/("""{"""),format.raw/*21.42*/(""" """),format.raw/*21.43*/("""onMessage(evt) """),format.raw/*21.58*/("""}"""),format.raw/*21.59*/(""";
    websocket.onerror = function(evt) """),format.raw/*22.39*/("""{"""),format.raw/*22.40*/(""" """),format.raw/*22.41*/("""onError(evt) """),format.raw/*22.54*/("""}"""),format.raw/*22.55*/(""";
  """),format.raw/*23.3*/("""}"""),format.raw/*23.4*/("""

  """),format.raw/*25.3*/("""function onOpen(evt)
  """),format.raw/*26.3*/("""{"""),format.raw/*26.4*/("""
    """),format.raw/*27.5*/("""writeToScreen("CONNECTED");
    doSend("WebSocket rocks");
  """),format.raw/*29.3*/("""}"""),format.raw/*29.4*/("""

  """),format.raw/*31.3*/("""function onClose(evt)
  """),format.raw/*32.3*/("""{"""),format.raw/*32.4*/("""
    """),format.raw/*33.5*/("""writeToScreen("DISCONNECTED");
  """),format.raw/*34.3*/("""}"""),format.raw/*34.4*/("""

  """),format.raw/*36.3*/("""function onMessage(evt)
  """),format.raw/*37.3*/("""{"""),format.raw/*37.4*/("""
    """),format.raw/*38.5*/("""writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
    websocket.close();
  """),format.raw/*40.3*/("""}"""),format.raw/*40.4*/("""

  """),format.raw/*42.3*/("""function onError(evt)
  """),format.raw/*43.3*/("""{"""),format.raw/*43.4*/("""
    """),format.raw/*44.5*/("""writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
  """),format.raw/*45.3*/("""}"""),format.raw/*45.4*/("""

  """),format.raw/*47.3*/("""function doSend(message)
  """),format.raw/*48.3*/("""{"""),format.raw/*48.4*/("""
    """),format.raw/*49.5*/("""writeToScreen("SENT: " + message);
    websocket.send(message);
  """),format.raw/*51.3*/("""}"""),format.raw/*51.4*/("""

  """),format.raw/*53.3*/("""function writeToScreen(message)
  """),format.raw/*54.3*/("""{"""),format.raw/*54.4*/("""
    """),format.raw/*55.5*/("""var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
  """),format.raw/*59.3*/("""}"""),format.raw/*59.4*/("""

  """),format.raw/*61.3*/("""window.addEventListener("load", init, false);

  </script>

<h2>WebSocket Test</h2>

<div id="output"></div>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Oct 05 17:07:20 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/index4.scala.html
                  HASH: 5782cc102c5a6b8193ac6ecaee92ee764ceec72e
                  MATRIX: 525->0|803->251|831->252|863->257|957->324|985->325|1016->329|1070->356|1098->357|1130->362|1229->433|1258->434|1287->435|1327->447|1356->448|1424->488|1453->489|1482->490|1523->503|1552->504|1622->546|1651->547|1680->548|1723->563|1752->564|1820->604|1849->605|1878->606|1919->619|1948->620|1979->624|2007->625|2038->629|2088->652|2116->653|2148->658|2236->719|2264->720|2295->724|2346->748|2374->749|2406->754|2466->787|2494->788|2525->792|2578->818|2606->819|2638->824|2767->926|2795->927|2826->931|2877->955|2905->956|2937->961|3036->1033|3064->1034|3095->1038|3149->1065|3177->1066|3209->1071|3302->1137|3330->1138|3361->1142|3422->1176|3450->1177|3482->1182|3647->1320|3675->1321|3706->1325
                  LINES: 16->1|26->11|26->11|27->12|29->14|29->14|31->16|32->17|32->17|33->18|34->19|34->19|34->19|34->19|34->19|35->20|35->20|35->20|35->20|35->20|36->21|36->21|36->21|36->21|36->21|37->22|37->22|37->22|37->22|37->22|38->23|38->23|40->25|41->26|41->26|42->27|44->29|44->29|46->31|47->32|47->32|48->33|49->34|49->34|51->36|52->37|52->37|53->38|55->40|55->40|57->42|58->43|58->43|59->44|60->45|60->45|62->47|63->48|63->48|64->49|66->51|66->51|68->53|69->54|69->54|70->55|74->59|74->59|76->61
                  -- GENERATED --
              */
          