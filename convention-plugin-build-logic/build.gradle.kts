plugins {
    kotlin("jvm")
    `kotlin-dsl`
}

group = "io.jenkins.gradle"
version = "0.1.0"

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven {
        url = uri("https://repo.jenkins-ci.org/public/")
    }
}

dependencies {
    // Jenkins Gradle JPI plugin
    implementation("org.jenkins-ci.tools:gradle-jpi-plugin:0.43.0")
    // Kotlin Gradle plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    // Code quality plugins
    implementation("com.diffplug.gradle.spotless:com.diffplug.gradle.spotless.gradle.plugin:7.0.2")
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}