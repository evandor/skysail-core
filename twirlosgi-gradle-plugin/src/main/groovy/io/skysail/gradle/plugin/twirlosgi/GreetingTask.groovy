package io.skysail.gradle.plugin.twirlosgi

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GreetingTask extends DefaultTask {
    String greeting = 'hello from GreetingTask'

    @TaskAction
    def greet() {
        println greeting
        println "compiling twirl sources..."
        //val compiler = new io.skysail.gradle.plugin.twirlosgi.SkysailTwirlCompiler()
        //compiler.compile()
        println "compiled"
    }
}
