plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

group = "io.jenkins.gradle"
version = "0.1.0"

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
    maven {
        url = uri("https://repo.maven.apache.org/maven2")
        metadataSources {
            mavenPom()
            artifact()
            ignoreGradleMetadataRedirection()
        }
    }
}

dependencies {
    // Jenkins Gradle JPI plugin
    implementation("org.jenkins-ci.tools:gradle-jpi-plugin:0.53.1")
    // Kotlin Gradle plugin and standard library
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:5.0.14")
    // Ktlint plugin
    implementation("org.jlleitschuh.gradle:ktlint-gradle:12.1.0")
}

kotlin {
    jvmToolchain(17)
}