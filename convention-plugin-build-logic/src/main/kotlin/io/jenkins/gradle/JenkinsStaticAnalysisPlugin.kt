package io.jenkins.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class JenkinsStaticAnalysisPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "org.jlleitschuh.gradle.ktlint")

            tasks.register("checkCodeStyle") {
                group = "Verification 🔍"
                description = "Checks code formatting"
                dependsOn("ktlintCheck")
            }

            tasks.register("formatCode") {
                group = "Formatting the code 🚀"
                description = "Formats code according to style guidelines"
                dependsOn("ktlintFormat")
            }
        }
    }
}