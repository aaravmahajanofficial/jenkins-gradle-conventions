plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "jenkins-gradle-conventions"

include("demo-jenkins-plugin")
includeBuild("convention-plugin-build-logic")
