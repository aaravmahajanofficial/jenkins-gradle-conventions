package io.jenkins.gradle

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories
import org.jenkinsci.gradle.plugins.jpi.JpiExtension
import org.jenkinsci.gradle.plugins.jpi.JpiPlugin

class JenkinsPluginConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {

            apply<JpiPlugin>() // we can reuse the Gradle JPI plugin

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            repositories {
                gradlePluginPortal()
                mavenCentral()
                maven {
                    url = uri("https://repo.jenkins-ci.org/public/")
                    metadataSources {
                        mavenPom()
                        artifact()
                        ignoreGradleMetadataRedirection()
                    }
                }
            }
            // any dev can rewrite these defaults in gradle.properties 👀
            extensions.configure<JpiExtension> {
                jenkinsVersion.set(project.findProperty("jenkins.version")?.toString() ?: "2.479")
                shortName = project.name
                displayName = project.name.capitalized()

                developers {
                    developer {
                        id.set("aaravmahajanofficial")
                        name.set("Aarav Mahajan")
                        email.set("aaravmahajan2003@gmail.com")
                    }
                }
            }

            dependencies {
                "jenkinsPlugins"("org.jenkins-ci.main:jenkins-core:${findProperty("jenkins.version") ?: "2.479"}")
                "jenkinsPlugins"("org.jenkins-ci.plugins:structs")
                "testImplementation"(
                    "org.jenkins-ci.main:jenkins-test-harness:${
                        findProperty("jenkins-test-harness.version") ?: "1720.vd973c953e3d3"
                    }"
                )
                "testImplementation"("org.jenkins-ci.tests:plugins-compat-tester-cli:1568.v7605c180b_094")
            }

            // BOM (Bill of Materials) for consistent dependency versions
            configureBom()
        }

    }

    private fun Project.configureBom() {
        val jenkinsVersion = findProperty("jenkins.version")?.toString() ?: "2.479"
        val bomVersion = when {
            jenkinsVersion.startsWith("2.4") -> "3208.vb_21177d4b_cd9"
            else -> "3208.vb_21177d4b_cd9" // Use the same version as the default
        }
        dependencies {
            "implementation"(platform("io.jenkins.tools.bom:bom-2.426.x:$bomVersion"))
            constraints {
                "implementation"("org.jenkins-ci.main:jenkins-core:${findProperty("jenkins.version") ?: "2.479"}")
            }
        }
    }
}



