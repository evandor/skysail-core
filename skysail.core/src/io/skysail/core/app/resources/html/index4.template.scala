
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main
import html.head

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
                  DATE: Wed Oct 04 08:13:26 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/index4.scala.html
                  HASH: 7e841c619b94d08fcd671f174f92756202d57693
                  MATRIX: 493->0|771->251|799->252|831->257|925->324|953->325|984->329|1038->356|1066->357|1098->362|1197->433|1226->434|1255->435|1295->447|1324->448|1392->488|1421->489|1450->490|1491->503|1520->504|1590->546|1619->547|1648->548|1691->563|1720->564|1788->604|1817->605|1846->606|1887->619|1916->620|1947->624|1975->625|2006->629|2056->652|2084->653|2116->658|2204->719|2232->720|2263->724|2314->748|2342->749|2374->754|2434->787|2462->788|2493->792|2546->818|2574->819|2606->824|2735->926|2763->927|2794->931|2845->955|2873->956|2905->961|3004->1033|3032->1034|3063->1038|3117->1065|3145->1066|3177->1071|3270->1137|3298->1138|3329->1142|3390->1176|3418->1177|3450->1182|3615->1320|3643->1321|3674->1325
                  LINES: 16->1|26->11|26->11|27->12|29->14|29->14|31->16|32->17|32->17|33->18|34->19|34->19|34->19|34->19|34->19|35->20|35->20|35->20|35->20|35->20|36->21|36->21|36->21|36->21|36->21|37->22|37->22|37->22|37->22|37->22|38->23|38->23|40->25|41->26|41->26|42->27|44->29|44->29|46->31|47->32|47->32|48->33|49->34|49->34|51->36|52->37|52->37|53->38|55->40|55->40|57->42|58->43|58->43|59->44|60->45|60->45|62->47|63->48|63->48|64->49|66->51|66->51|68->53|69->54|69->54|70->55|74->59|74->59|76->61
                  -- GENERATED --
              */
          