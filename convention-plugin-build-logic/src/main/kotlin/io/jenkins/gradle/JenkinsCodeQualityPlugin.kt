package io.jenkins.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.tasks.JacocoReport

class JenkinsCodeQualityPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        with(project) {

            apply(plugin = "com.github.spotbugs")
            apply(plugin = "jacoco")

            // Configure SpotBugs
            configure<com.github.spotbugs.snom.SpotBugsExtension> {
                toolVersion.set("4.7.3")
                ignoreFailures.set(false)
                effort.set(com.github.spotbugs.snom.Effort.MAX)
                reportLevel.set(com.github.spotbugs.snom.Confidence.HIGH)
            }

            // Configure JaCoCo
            tasks.withType<JacocoReport> {
                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }
            }

            dependencies {
                "spotbugsPlugins"("com.h3xstream.findsecbugs:findsecbugs-plugin:1.12.0")
            }


        }

    }

}