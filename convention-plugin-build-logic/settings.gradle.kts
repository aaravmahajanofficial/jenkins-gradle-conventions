dependencyResolutionManagement {
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
    }
}

rootProject.name = "convention-plugin-build-logic"