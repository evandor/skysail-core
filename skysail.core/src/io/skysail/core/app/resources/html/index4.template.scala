
package io.skysail.core.app.resources.html


object index4 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<meta charset="utf-8" />
<title>WebSocket Test</title>
<script language="javascript" type="text/javascript">

  var wsUri = "ws://echo.websocket.org/";
  var output;

  function init()
  """),format.raw/*10.3*/("""{"""),format.raw/*10.4*/("""
    """),format.raw/*11.5*/("""output = document.getElementById("output");
    testWebSocket();
  """),format.raw/*13.3*/("""}"""),format.raw/*13.4*/("""

  """),format.raw/*15.3*/("""function testWebSocket()
  """),format.raw/*16.3*/("""{"""),format.raw/*16.4*/("""
    """),format.raw/*17.5*/("""websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) """),format.raw/*18.38*/("""{"""),format.raw/*18.39*/(""" """),format.raw/*18.40*/("""onOpen(evt) """),format.raw/*18.52*/("""}"""),format.raw/*18.53*/(""";
    websocket.onclose = function(evt) """),format.raw/*19.39*/("""{"""),format.raw/*19.40*/(""" """),format.raw/*19.41*/("""onClose(evt) """),format.raw/*19.54*/("""}"""),format.raw/*19.55*/(""";
    websocket.onmessage = function(evt) """),format.raw/*20.41*/("""{"""),format.raw/*20.42*/(""" """),format.raw/*20.43*/("""onMessage(evt) """),format.raw/*20.58*/("""}"""),format.raw/*20.59*/(""";
    websocket.onerror = function(evt) """),format.raw/*21.39*/("""{"""),format.raw/*21.40*/(""" """),format.raw/*21.41*/("""onError(evt) """),format.raw/*21.54*/("""}"""),format.raw/*21.55*/(""";
  """),format.raw/*22.3*/("""}"""),format.raw/*22.4*/("""

  """),format.raw/*24.3*/("""function onOpen(evt)
  """),format.raw/*25.3*/("""{"""),format.raw/*25.4*/("""
    """),format.raw/*26.5*/("""writeToScreen("CONNECTED");
    doSend("WebSocket rocks");
  """),format.raw/*28.3*/("""}"""),format.raw/*28.4*/("""

  """),format.raw/*30.3*/("""function onClose(evt)
  """),format.raw/*31.3*/("""{"""),format.raw/*31.4*/("""
    """),format.raw/*32.5*/("""writeToScreen("DISCONNECTED");
  """),format.raw/*33.3*/("""}"""),format.raw/*33.4*/("""

  """),format.raw/*35.3*/("""function onMessage(evt)
  """),format.raw/*36.3*/("""{"""),format.raw/*36.4*/("""
    """),format.raw/*37.5*/("""writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
    websocket.close();
  """),format.raw/*39.3*/("""}"""),format.raw/*39.4*/("""

  """),format.raw/*41.3*/("""function onError(evt)
  """),format.raw/*42.3*/("""{"""),format.raw/*42.4*/("""
    """),format.raw/*43.5*/("""writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
  """),format.raw/*44.3*/("""}"""),format.raw/*44.4*/("""

  """),format.raw/*46.3*/("""function doSend(message)
  """),format.raw/*47.3*/("""{"""),format.raw/*47.4*/("""
    """),format.raw/*48.5*/("""writeToScreen("SENT: " + message);
    websocket.send(message);
  """),format.raw/*50.3*/("""}"""),format.raw/*50.4*/("""

  """),format.raw/*52.3*/("""function writeToScreen(message)
  """),format.raw/*53.3*/("""{"""),format.raw/*53.4*/("""
    """),format.raw/*54.5*/("""var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
  """),format.raw/*58.3*/("""}"""),format.raw/*58.4*/("""

  """),format.raw/*60.3*/("""window.addEventListener("load", init, false);

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
                  DATE: Tue Oct 03 19:07:43 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/io/skysail/core/app/resources/index4.scala.html
                  HASH: c6b8322e6d0ce4831e8a73fe821a98c6b916def9
                  MATRIX: 432->0|662->203|690->204|722->209|816->276|844->277|875->281|929->308|957->309|989->314|1088->385|1117->386|1146->387|1186->399|1215->400|1283->440|1312->441|1341->442|1382->455|1411->456|1481->498|1510->499|1539->500|1582->515|1611->516|1679->556|1708->557|1737->558|1778->571|1807->572|1838->576|1866->577|1897->581|1947->604|1975->605|2007->610|2095->671|2123->672|2154->676|2205->700|2233->701|2265->706|2325->739|2353->740|2384->744|2437->770|2465->771|2497->776|2626->878|2654->879|2685->883|2736->907|2764->908|2796->913|2895->985|2923->986|2954->990|3008->1017|3036->1018|3068->1023|3161->1089|3189->1090|3220->1094|3281->1128|3309->1129|3341->1134|3506->1272|3534->1273|3565->1277
                  LINES: 13->1|22->10|22->10|23->11|25->13|25->13|27->15|28->16|28->16|29->17|30->18|30->18|30->18|30->18|30->18|31->19|31->19|31->19|31->19|31->19|32->20|32->20|32->20|32->20|32->20|33->21|33->21|33->21|33->21|33->21|34->22|34->22|36->24|37->25|37->25|38->26|40->28|40->28|42->30|43->31|43->31|44->32|45->33|45->33|47->35|48->36|48->36|49->37|51->39|51->39|53->41|54->42|54->42|55->43|56->44|56->44|58->46|59->47|59->47|60->48|62->50|62->50|64->52|65->53|65->53|66->54|70->58|70->58|72->60
                  -- GENERATED --
              */
          