plugins {
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
    implementation(kotlin("stdlib-jdk8"))

    // Jenkins Gradle JPI plugin
    implementation("org.jenkins-ci.tools:gradle-jpi-plugin:0.43.0")
    // Kotlin Gradle plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    // Code quality plugins
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.25.0")
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:3.1.0")

}
kotlin {
    jvmToolchain(21)
}