package io.skysail.core.compiler

abstract class Plant

case class Rose() extends Plant

abstract class Greenhouse {
  def getPlant(): Plant
}

case class GreenhouseFactory(implFilename: String) {
  import reflect.runtime.currentMirror
  import tools.reflect.ToolBox
  val toolbox = currentMirror.mkToolBox()
  import scala.io.Source

  val fileContents = Source.fromFile(implFilename).getLines.mkString("\n")
  val tree = toolbox.parse("import io.skysail.core.compiler._; " + fileContents)
  val compiledCode = toolbox.compile(tree)

  def make(): Greenhouse = compiledCode().asInstanceOf[Greenhouse]
}

object Main {
  def main(args: Array[String]) {
    val greenhouseFactory = GreenhouseFactory("/Users/carsten/git/skysail-core/skysail.core/external.stmp")
    val greenhouse = greenhouseFactory.make()
    val p = greenhouse.getPlant()

    println(p)
  }
}
