package io.skysail.core.compiler

abstract class Plant



case class Rose() extends Plant

abstract class SkysailRoutes {
  def getPlant(): Plant
}

case class SkysailRoutesFactory(implFilename: String) {
  import reflect.runtime.currentMirror
  import tools.reflect.ToolBox
  val toolbox = currentMirror.mkToolBox()
  import scala.io.Source

  val fileContents = Source.fromFile(implFilename).getLines.mkString("\n")

  val routesScalaCode: String = {
    s"""
    import io.skysail.core.compiler._;
    import akka.http.scaladsl.server.{PathMatcher, PathMatchers};
    import scala.reflect.internal.StdAttachments;

    new SkysailRoutes {
       def getPlant() = new Rose()

       def routes(): List[io.skysail.core.app.RouteMapping2] = {
         List(
           $fileContents
           io.skysail.core.app.RouteMapping2("bundles/:id", PathMatcher("bundles") / "id", classOf[io.skysail.core.app.resources.BundleResource])
         )
       }

       //"bundles/:id" >>> BundleResource

    }
    """
  }

  println (routesScalaCode)

  val tree = toolbox.parse(routesScalaCode)
  val compiledCode = toolbox.compile(tree)

  def make(): SkysailRoutes = compiledCode().asInstanceOf[SkysailRoutes]
}

object Main {
  def main(args: Array[String]) {
    val greenhouseFactory = SkysailRoutesFactory("/Users/carsten/git/skysail-core/skysail.core/routes.stmp")
    val greenhouse = greenhouseFactory.make()
    val p = greenhouse.getPlant()

    println(p)
  }
}
