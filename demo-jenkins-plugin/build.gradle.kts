plugins {
    kotlin("jvm") version "1.9.24"
    id("jenkins-plugin")
    id("jenkins-static")
    id("jenkins-test")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

group = "io.jenkins.userPlugins"
version = "0.1.0"

// Plugin-specific dependencies
dependencies {
//    implementation("org.jenkins-ci.plugins:plain-credentials:183.va_de8f1dd5a_2b_")
//    implementation("org.jenkins-ci.plugins:credentials:2.11.0")
}
