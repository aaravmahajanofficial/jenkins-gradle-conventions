import io.jenkins.gradle.JenkinsTestConventionPlugin

apply<JenkinsTestConventionPlugin>()

// with this method, no need to manually register JenkinsPluginConventionPlugin.kt in Gradle's plugin system
// otherwise would have to use,

/* in convention-plugin-build-logic/build.gradle.kts

     gradlePlugin {
        plugins {
            create("jenkinsPlugin") {
                id = "io.jenkins.gradle.jenkins-plugin"
                implementationClass = "io.jenkins.gradle.JenkinsPluginConventionPlugin"
            }
        }
    }

 */