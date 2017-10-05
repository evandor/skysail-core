
package io.skysail.core.app.resources.html

import play.twirl.api.Html
import html.main

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
                  DATE: Wed Oct 04 19:41:18 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/index4.scala.html
                  HASH: 4abb0fac516b3c328c82527bb730e7ad2d52e4c9
                  MATRIX: 476->0|754->251|782->252|814->257|908->324|936->325|967->329|1021->356|1049->357|1081->362|1180->433|1209->434|1238->435|1278->447|1307->448|1375->488|1404->489|1433->490|1474->503|1503->504|1573->546|1602->547|1631->548|1674->563|1703->564|1771->604|1800->605|1829->606|1870->619|1899->620|1930->624|1958->625|1989->629|2039->652|2067->653|2099->658|2187->719|2215->720|2246->724|2297->748|2325->749|2357->754|2417->787|2445->788|2476->792|2529->818|2557->819|2589->824|2718->926|2746->927|2777->931|2828->955|2856->956|2888->961|2987->1033|3015->1034|3046->1038|3100->1065|3128->1066|3160->1071|3253->1137|3281->1138|3312->1142|3373->1176|3401->1177|3433->1182|3598->1320|3626->1321|3657->1325
                  LINES: 15->1|25->11|25->11|26->12|28->14|28->14|30->16|31->17|31->17|32->18|33->19|33->19|33->19|33->19|33->19|34->20|34->20|34->20|34->20|34->20|35->21|35->21|35->21|35->21|35->21|36->22|36->22|36->22|36->22|36->22|37->23|37->23|39->25|40->26|40->26|41->27|43->29|43->29|45->31|46->32|46->32|47->33|48->34|48->34|50->36|51->37|51->37|52->38|54->40|54->40|56->42|57->43|57->43|58->44|59->45|59->45|61->47|62->48|62->48|63->49|65->51|65->51|67->53|68->54|68->54|69->55|73->59|73->59|75->61
                  -- GENERATED --
              */
          