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
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            repositories {
                mavenCentral()
                maven {
                    url = uri("https://repo.jenkins-ci.org/public/")
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
                "testImplementation"("io.jenkins.plugins:plugin-compat-tester")
            }

            // BOM (Bill of Materials) for consistent dependency versions
            configureBom()
        }

    }

    private fun Project.configureBom() {
        val jenkinsVersion = findProperty("jenkins.version")?.toString() ?: "2.479"
        val (bomArtifact, bomVersion) = when {
            jenkinsVersion.startsWith("2.346") -> "bom-2.346.x" to "1763.v092b_8980a_f5e"
            jenkinsVersion.startsWith("2.361") -> "bom-2.361.x" to "2102.v854b_fec19c92"
            jenkinsVersion.startsWith("2.375") -> "bom-2.375.x" to "2198.v39c76fc308ca"
            jenkinsVersion.startsWith("2.387") -> "bom-2.387.x" to "2543.vfb_1a_5fb_9496d"
            jenkinsVersion.startsWith("2.401") -> "bom-2.401.x" to "2745.vc7b_fe4c876fa_x"
            jenkinsVersion.startsWith("2.414") -> "bom-2.414.x" to "2982.vdce2153031a_0"
            jenkinsVersion.startsWith("2.426") -> "bom-2.426.x" to "3208.vb_21177d4b_cd9"
            jenkinsVersion.startsWith("2.440") -> "bom-2.440.x" to "3435.v238d66a_043fb_"
            jenkinsVersion.startsWith("2.452") -> "bom-2.452.x" to "3944.v1a_e4f8b_452db_"
            jenkinsVersion.startsWith("2.462") -> "bom-2.462.x" to "4228.v0a_71308d905b_"
            jenkinsVersion.startsWith("2.479") -> "bom-2.479.x" to "4495.v14e8721f61e48"
            else -> "bom-2.479.x" to "4495.v14e8721f61e48"
        }

        dependencies {
            "implementation"(platform("io.jenkins.tools.bom:$bomArtifact:$bomVersion"))
        }
    }
}



