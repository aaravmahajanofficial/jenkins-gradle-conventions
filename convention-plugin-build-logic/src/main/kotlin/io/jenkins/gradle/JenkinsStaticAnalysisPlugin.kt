package io.jenkins.gradle

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class JenkinsStaticAnalysisPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply(plugin = "com.diffplug.spotless")

            extensions.configure<SpotlessExtension> {
                kotlin {
                    // Target all Kotlin files but exclude build directories
                    target("**/*.kt")
                    targetExclude("**/build/**", "**/build-*/**")

                    // Use ktlint with latest stable version
                    ktlint("1.0.0")
                        .setEditorConfigPath("$projectDir/.editorconfig")
                        .editorConfigOverride(mapOf(
                            "max_line_length" to "120",
                            "ktlint_code_style" to "intellij_idea",
                            "indent_size" to "4"
                        ))

                    // Toggle formatting on/off capability
                    toggleOffOn()

                    // Remove unused imports
                    trimTrailingWhitespace()
                    endWithNewline()

                    // Add license header
                    licenseHeader("/* (C) \$YEAR */")
                }

                kotlinGradle {
                    target("*.gradle.kts", "buildSrc/**/*.kt")
                    ktlint()
                    trimTrailingWhitespace()
                    endWithNewline()
                }
            }

            tasks.register("checkCodeStyle") {
                group = "Verification 🔍"
                description = "Checks code formatting"
                dependsOn("spotlessCheck")
            }

            tasks.register("formatCode") {
                group = "Formatting the code 🚀"
                description = "Formats code according to style guidelines"
                dependsOn("spotlessApply")
            }
        }
    }
}