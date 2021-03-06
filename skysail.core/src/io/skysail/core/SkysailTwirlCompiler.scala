//package io.skysail.core
//
//import java.io.File
//import play.twirl.compiler.TwirlCompiler
//import scala.io.Codec
//
//// https://github.com/csokol/vraptor-twirl/blob/master/src/main/java/br/com/caelum/vraptor/twirl/Compiler.java
//object SkysailTwirlCompiler {
//
//  private val root = new File("./resources/templates")
//  private val outputFolder = new File("./src")
//
//  def main(args: Array[String]) {
//    println("Hello, world!")
//    compileDir(root)
//  }
//
//  def compileDir(viewsFolder: File): Unit = {
//    val possibleViews = viewsFolder.listFiles
//    for (view <- possibleViews) {
//      if (view.isDirectory) compileDir(view) else compile(view)
//    }
//  }
//
//  var additionalImports: scala.Seq[_root_.scala.Predef.String] = List(
//    "play.twirl.api.Html",
//    "html.main",
//    "io.skysail.core.model.RepresentationModel"
//  )
//
//  var constructorAnnotations: scala.Seq[_root_.scala.Predef.String] = Nil
//
//  def compile(view: File): Unit = {
//    System.out.println("Generating scala file " + view.getAbsolutePath)
//    TwirlCompiler.compile(view, root, outputFolder, "play.twirl.api.HtmlFormat", additionalImports, constructorAnnotations, Codec(scala.util.Properties.sourceEncoding), false)
//  }
//}