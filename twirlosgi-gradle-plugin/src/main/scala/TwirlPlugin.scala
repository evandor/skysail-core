package io.skysail.gradle.plugin.twirlosgi

import org.gradle.api.Plugin
import org.gradle.api.Project

class TwirlPlugin extends Plugin[Project] {
  override def apply(project: Project) = {
    //project.task("hello")
    //project.task("hello", classOf[GreetingTask])
    println (" > hier")
    println (" > " + project.getProjectDir)
    //println (" > " + greeting)
    println (" > compiling twirl sources...")
    val compiler = new io.skysail.gradle.plugin.twirlosgi.SkysailTwirlCompiler(project.getProjectDir)
    println (" > " + compiler)
    compiler.compileDir()
    println (" > compiled")
  }
}
