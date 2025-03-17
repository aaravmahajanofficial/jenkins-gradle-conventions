# Jenkins Gradle Convention Plugin Prototype

A modern Gradle-based convention plugin for Jenkins plugin development, providing a standardized build configuration and
development experience.

## Aim of the Project:
Implementing a new convention plugin for Jenkins Plugins that would cover the main features of Jenkins plugin Parent POM, with Kotlin and Kotlin DSL. This would include not just building the plugin, but also testing and static analysis according to Jenkins' best practices

## But what are the limitations of the Gradle JPI plugin? 🤔

- No tutorials/documentation
- Not following Jenkins hosting requirements
- Dependency & BOM Management Challenges - No, seamless dependency mgmt as Parent POM
- Very limited test and static analysis setup
- Verbose & Repetitive Configuration
- .... etc.

## Overview

According to Gradle Documentation:

`A convention plugin serves as a modern alternative to the Jenkins Parent POM, bringing the same robust development
experience to Gradle-based Jenkins plugin development. It provides a modular, extensible approach to Jenkins plugin
development with modern tooling and best practices.`

## But, how convention plugin going to help me? 🤔

| Feature                  | Without Convention Plugin                        | With Convention Plugin                     |
|--------------------------|--------------------------------------------------|-------------------------------------------|
| **Build File Length**    | Long, repetitive                                 | Short, concise                           |
| **Dependency Management**| Manually added for each project                  | Centralized in convention plugin         |
| **Testing Setup**        | Each project must configure Jenkins Test Harness | Automatically configured                 |
| **Static Analysis**      | Each project configures Spotless separately      | Automatically applied                    |
| **Code Formatting**      | Might differ across projects                     | Enforced consistency                     |
| **Maintenance**          | Hard to update all projects                      | Update once in the convention plugin     |

### TL;DR 😀

- All the hardwork would be done by my convention plugin, like creating a (.jpi) file for Jenkins extensions, static testing, code analysis etc. would be added by my convention plugin and the developers would just need to add my convention plugin like `jenkins-plugin`, `jenkins-test-plugin` etc. to use enable all these features.
- They only need to focus on the making Jenkins Plugin.
- Just like a plug-and-play system
- The devs would be able to customize the defaults similar to `Parent POM for Jenkins Plugins`

## Current Features
```text
# build-logic

./convention-plugin-build-logic
```

### Core Plugins

1. **Jenkins Plugin Convention** (`jenkins-plugin`)
    - Core Jenkins plugin configuration
    - Standard build setup
    - Repository management
    - Basic dependency management

2. **Static Analysis** (`jenkins-static`)
    - SpotBugs integration
    - Ktlint for Kotlin code
    - Code quality checks

3. **Testing Convention** (`jenkins-test`)
    - Standardized test configuration
    - Test framework setup
    - Test reporting

4. **Code Quality** (`jenkins-codequality`)
    - Code style enforcement
    - Quality gates
    - Best practices enforcement

### My tech stack

- **Gradle**
- **Kotlin**: DSL and implementation language
- **Java 17**: Target JVM version
- **Gradle JPI Plugin**: Core Jenkins plugin development
  - `https://github.com/jenkinsci/gradle-jpi-plugin`
- **SpotBugs**: Static analysis
- **Ktlint**: Kotlin code style enforcement

### Gradle Version
```bash
------------------------------------------------------------
Gradle 8.10
------------------------------------------------------------

Build time:    2024-08-14 11:07:45 UTC
Revision:      fef2edbed8af1022cefaf44d4c0514c5f89d7b78

Kotlin:        1.9.24
Groovy:        3.0.22
Ant:           Apache Ant(TM) version 1.10.14 compiled on August 16 2023
Launcher JVM:  17.0.14 (Amazon.com Inc. 17.0.14+7-LTS)
Daemon JVM:    C:\Users\aarav\.jdks\corretto-17.0.14 (no JDK specified, using current Java home)
OS:            Windows 11 10.0 amd64
```

## Usage

### Basic Setup

```kotlin
plugins {
    id("jenkins-plugin")
    id("jenkins-static")
    id("jenkins-test")
    id("jenkins-codequality")
}

group = "io.jenkins.userPlugins"
version = "0.1.0"
```

### What is missing in comparison to Parent POM for Jenkins Plugin? 👀

1. **Version Management**
    - [ ] Version catalogs for dependency management
    - [ ] Centralized version control
    - [ ] Automated version updates

2. **Release Management**
    - [ ] Release automation
    - [ ] Signing configuration
    - [ ] Release notes generation

3. **Compatibility Testing**
    - [ ] Multi-Jenkins version testing
    - [ ] Compatibility matrix
    - [ ] Automated compatibility reports

4. **Documentation**
    - [ ] Documentation generation
    - [ ] API documentation
    - [ ] Usage guides

5. **Security**
    - [ ] Security scanning
    - [ ] Vulnerability checks
    - [ ] Security reporting

6. **Dependency Management**
    - [ ] Dependency exclusions
    - [ ] Version overrides
    - [ ] Dependency updates

7. **Repository Management**
    - [ ] Repository configuration
    - [ ] Artifact publishing
    - [ ] Repository validation

8. **Plugin Dependencies**
    - [ ] Common plugin management
    - [ ] Version compatibility
    - [ ] Dependency resolution

9. **Code Quality**
    - [ ] Additional static analysis tools
    - [ ] Code coverage reporting
    - [ ] Performance testing

10. **Developer Experience**
    - [ ] IDE integration
    - [ ] Development tools
    - [ ] Debugging support

### Feature Parity

Currently implemented core features from Parent POM and some improvements:

- ✅ Basic plugin configuration
- ✅ Static analysis
- ✅ Testing framework
- ✅ Code quality checks
- ✅ Repository management
- ✅ Basic dependency management

### `./gradlew tasks`

```bash
Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildKotlinToolingMetadata - Build metadata json file containing information about the used Kotlin tooling
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
createVersionlessLookup - Creates plugin lookup without versions in filenames
generatedJenkinsTestClasses - Assembles generated jenkins test classes.
generateJenkinsManifest - Generate manifest for Jenkins plugin
generateJenkinsPluginClassManifest - Finds sole hudson.Plugin subclass for Manifest
generateJenkinsPluginDependenciesManifest - Finds optional and required plugin dependencies
generateJenkinsSupportDynamicLoadingManifest - Aggregates dynamic loading values of @Extensions
generateLicenseInfo - Generates license information.
jar - Assembles a jar archive containing the classes of the 'main' feature.
javadocJar - Assembles a jar archive containing the main javadoc.
jpi - Generates the JPI package
kotlinSourcesJar - Assembles a jar archive containing the sources of target 'kotlin'.
localizeMessages - Generates Java source files for **/Messages.properties
localizer - [deprecated] See localizeMessages. Generates the Java source for the localizer.
sourcesJar - Assembles a jar archive containing the main sources.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
updateDaemonJvm - Generates or updates the Gradle Daemon JVM criteria.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
groovydoc - Generates Groovydoc API documentation for the main source code.
javadoc - Generates Javadoc API documentation for the 'main' feature.

Formatting tasks
----------------
ktlintFormat - Runs the ktlint formatter on all kotlin sources in this project.

Formatting the code ? tasks
----------------------------
formatCode - Formats code according to style guidelines

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'jenkins-gradle-conventions'.
dependencies - Displays all dependencies declared in root project 'jenkins-gradle-conventions'.
dependencyInsight - Displays the insight into a specific dependency in root project 'jenkins-gradle-conventions'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
kotlinDslAccessorsReport - Prints the Kotlin code for accessing the currently available project extensions and conventions.
ktlintGenerateBaseline - Generates KtLint baseline file
outgoingVariants - Displays the outgoing variants of root project 'jenkins-gradle-conventions'.
projects - Displays the sub-projects of root project 'jenkins-gradle-conventions'.
properties - Displays the properties of root project 'jenkins-gradle-conventions'.
resolvableConfigurations - Displays the configurations that can be resolved in root project 'jenkins-gradle-conventions'.
tasks - Displays the tasks runnable from root project 'jenkins-gradle-conventions' (some of the displayed tasks may belong to subprojects).

Jenkins Server tasks
--------------------
generateJenkinsServerHpl - Generate hpl (Hudson plugin link) for running locally
installJenkinsServerPlugins - Install plugins to the server's Jenkins Home directory
server - Run Jenkins server locally with the plugin being developed

Publishing tasks
----------------
generateMetadataFileForMavenJpiPublication - Generates the Gradle metadata file for publication 'mavenJpi'.
generatePomFileForMavenJpiPublication - Generates the Maven POM file for publication 'mavenJpi'.
publish - Publishes all publications produced by this project.
publishAllPublicationsToJenkinsIncrementalsRepository - Publishes all Maven publications produced by this project to the jenkinsIncrementals repository.
publishAllPublicationsToJenkinsRepository - Publishes all Maven publications produced by this project to the jenkins repository.
publishMavenJpiPublicationToJenkinsIncrementalsRepository - Publishes Maven publication 'mavenJpi' to Maven repository 'jenkinsIncrementals'.
publishMavenJpiPublicationToJenkinsRepository - Publishes Maven publication 'mavenJpi' to Maven repository 'jenkins'.
publishMavenJpiPublicationToMavenLocal - Publishes Maven publication 'mavenJpi' to the local Maven repository.
publishToMavenLocal - Publishes all Maven publications produced by this project to the local Maven cache.

Verification tasks
------------------
check - Runs all checks.
checkAccessModifier - Checks if Jenkins restricted apis are used (https://tiny.cc/jenkins-restricted).
checkKotlinGradlePluginConfigurationErrors - Checks that Kotlin Gradle Plugin hasn't reported project configuration errors, failing otherwise. This task always runs before compileKotlin* or similar tasks.
checkOverlappingSources - Validate
copyGeneratedJenkinsTestPluginDependencies - Copies plugins on runtimeClasspath into directory for jenkins-test-harness to load in generatedJenkinsTest
copyTestPluginDependencies - Copies plugins on testRuntimeClasspath into directory for jenkins-test-harness to load in test
generatedJenkinsTest - Runs tests from org.jvnet.hudson.test.PluginAutomaticTestBuilder
generateJenkinsTests - Generates a test class that runs org.jvnet.hudson.test.PluginAutomaticTestBuilder
insertTest - [deprecated] Generates a Jenkins Test
jacocoTestCoverageVerification - Verifies code coverage metrics based on specified rules for the test task.
jacocoTestReport - Generates code coverage report for the test task.
ktlintCheck - Runs ktlint on all kotlin sources in this project.
spotbugsGeneratedJenkinsTest - Run SpotBugs analysis for the source set 'generatedJenkinsTest'
spotbugsMain - Run SpotBugs analysis for the source set 'main'
spotbugsTest - Run SpotBugs analysis for the source set 'test'
test - Runs the test suite.

Verification ? tasks
---------------------
checkCodeStyle - Checks code formatting
```

### References

How to Build a Custom Gradle Plugin to Share Project Config - Multi-Module Architecture -
Philipp Lackner
https://www.youtube.com/watch?v=kFWmL5opJNk

https://github.com/jenkinsci/plugin-pom
https://github.com/jenkinsci/gradle-jpi-plugin
https://docs.gradle.org/current/userguide/custom_plugins.html
https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html
