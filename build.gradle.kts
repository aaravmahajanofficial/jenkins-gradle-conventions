plugins {
    kotlin("jvm") version "2.1.10"
}

group = "io.jenkins.gradle"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}