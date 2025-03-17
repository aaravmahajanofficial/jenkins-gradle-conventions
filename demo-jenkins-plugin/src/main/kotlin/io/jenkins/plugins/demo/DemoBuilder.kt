package io.jenkins.plugins.demo

import hudson.Extension
import hudson.FilePath
import hudson.Launcher
import hudson.model.AbstractProject
import hudson.model.Run
import hudson.model.TaskListener
import hudson.tasks.BuildStepDescriptor
import hudson.tasks.Builder
import jenkins.tasks.SimpleBuildStep
import org.jenkinsci.Symbol
import org.kohsuke.stapler.DataBoundConstructor
import org.kohsuke.stapler.DataBoundSetter

class DemoBuilder @DataBoundConstructor constructor(private val message: String) : SimpleBuildStep {
    
    @set:DataBoundSetter
    var isUpperCase: Boolean = false

    override fun perform(run: Run<*, *>, workspace: FilePath, launcher: Launcher, listener: TaskListener) {
        val finalMessage = if (isUpperCase) message.uppercase() else message
        listener.logger.println("Demo Plugin Message: $finalMessage")
    }

    @Extension
    @Symbol("demoStep")
    class DescriptorImpl : BuildStepDescriptor<Builder>() {
        override fun isApplicable(jobType: Class<out AbstractProject<*, *>>): Boolean = true
        
        override fun getDisplayName(): String = "Demo Builder Step"
    }
} 