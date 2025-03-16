plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
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
        url = uri("https://repo.jenkins-ci.org/releases/")
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
    // Kotlin Gradle plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    // Code quality plugins
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.25.0")
}
kotlin {
    jvmToolchain(21)
}