plugins {
    id("jenkins-plugin")
    id("jenkins-static")
    id("jenkins-test")
}

group = "io.jenkins.userPlugins"
version = "0.1.0"

//repositories {
//    mavenCentral()
//    maven {
//        url = uri("https://repo.jenkins-ci.org/public/")
//        metadataSources {
//            mavenPom()
//            artifact()
//            ignoreGradleMetadataRedirection()
//        }
//    }
//}

// Plugin-specific dependencies
dependencies {
//    implementation("org.jenkins-ci.plugins:plain-credentials:183.va_de8f1dd5a_2b_")
//    implementation("org.jenkins-ci.plugins:credentials:2.11.0")
}
