package io.skysail.gradle.plugin.twirlosgi

import org.gradle.api.Project

class WebdriverExtension {

    String outputPath

    WebdriverExtension(Project project) {
        this.outputPath = "$project.buildDir/webdriver"
    }
}
