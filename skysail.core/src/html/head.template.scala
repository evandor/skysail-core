
package html

import play.twirl.api.Html
import html.main

object head extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.3*/("""<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Skysail</title>

  <link rel="shortcut icon" href="/webapp/favicon.ico" type="image/x-icon" >
  <link rel="stylesheet"    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css" async>
  <link rel="stylesheet"    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
  <link rel="stylesheet"    href="https://fonts.googleapis.com/css?family=Inconsolata|Roboto|Slabo+27px" async>

    <!--<script type="text/javascript" src="/_ui/jquery/3.1.1/jquery-3.1.1.min.js"></script>

    <link href="/webapp/css/contextMenu/jquery.contextMenu.css" rel="stylesheet" type="text/css" async />
    <script type="text/javascript" src="/webapp/js/contextMenu/jquery.contextMenu.js" async></script>

    <script type="text/javascript" src="/_polymer/bower_components/noty/js/noty/packaged/jquery.noty.packaged.min.js"></script>
    -->
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <!-- <script type="text/javascript" src="/_polymer/bower_components/webcomponentsjs/webcomponents-lite.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/webapp/css/hover/hover-min.css">-->

  <!-- Custom styles for this template -->
  <link href="custom.css" rel="stylesheet">"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Oct 05 07:44:58 CEST 2017
                  SOURCE: /Users/carsten/git/skysail-core/skysail.core/./resources/templates/head.scala.html
                  HASH: 6b72c63f71929f13b2a42ef5f1b60722542fbdc4
                  MATRIX: 444->2
                  LINES: 15->1
                  -- GENERATED --
              */
          