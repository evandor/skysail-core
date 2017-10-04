
package io.skysail.core.app.resources.html

import play.twirl.api.Html

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
                  DATE: Wed Oct 04 17:34:27 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/index4.scala.html
                  HASH: 525378655a544274f71b9ec9f7e8d4f9f6dfc75a
                  MATRIX: 459->0|737->251|765->252|797->257|891->324|919->325|950->329|1004->356|1032->357|1064->362|1163->433|1192->434|1221->435|1261->447|1290->448|1358->488|1387->489|1416->490|1457->503|1486->504|1556->546|1585->547|1614->548|1657->563|1686->564|1754->604|1783->605|1812->606|1853->619|1882->620|1913->624|1941->625|1972->629|2022->652|2050->653|2082->658|2170->719|2198->720|2229->724|2280->748|2308->749|2340->754|2400->787|2428->788|2459->792|2512->818|2540->819|2572->824|2701->926|2729->927|2760->931|2811->955|2839->956|2871->961|2970->1033|2998->1034|3029->1038|3083->1065|3111->1066|3143->1071|3236->1137|3264->1138|3295->1142|3356->1176|3384->1177|3416->1182|3581->1320|3609->1321|3640->1325
                  LINES: 14->1|24->11|24->11|25->12|27->14|27->14|29->16|30->17|30->17|31->18|32->19|32->19|32->19|32->19|32->19|33->20|33->20|33->20|33->20|33->20|34->21|34->21|34->21|34->21|34->21|35->22|35->22|35->22|35->22|35->22|36->23|36->23|38->25|39->26|39->26|40->27|42->29|42->29|44->31|45->32|45->32|46->33|47->34|47->34|49->36|50->37|50->37|51->38|53->40|53->40|55->42|56->43|56->43|57->44|58->45|58->45|60->47|61->48|61->48|62->49|64->51|64->51|66->53|67->54|67->54|68->55|72->59|72->59|74->61
                  -- GENERATED --
              */
          