package io.jenkins.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

// adds JUnit, Jenkins test harness, and testing parameters, similar to Parent POM for Jenkins
class JenkinsTestConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        with(project) {

            tasks.withType<Test> {

                // for JUnit 5 support
                useJUnitPlatform()

                // test harness settings
                systemProperty("jenkins.test.timeout", findProperty("jenkins.test.timeout") ?: "180")
                systemProperty("jenkins.test.noFork", findProperty("jenkins.test.noFork") ?: "true")

                if (findProperty("release.skipTests")?.toString()?.toBoolean() == true) {
                    enabled = false // skip this task 🥸
                }

                // memory settings

                maxHeapSize = "1g"

                // test logging configuration
                testLogging {
                    events("passed", "skipped", "failed")
                }

            }

            dependencies {
                "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.10.1")
                "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.10.1")
                "testImplementation"("io.jenkins:configuration-as-code:1569.v7dc37580cd0d")
            }

        }

    }

}