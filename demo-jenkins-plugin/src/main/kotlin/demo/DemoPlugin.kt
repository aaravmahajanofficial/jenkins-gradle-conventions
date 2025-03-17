package io.jenkins.plugins.sample

import hudson.Extension
import hudson.model.RootAction

@Extension
class DemoPlugin : RootAction {
    override fun getIconFileName(): String = "symbol-star"

    override fun getDisplayName(): String = "Sample Plugin"

    override fun getUrlName(): String = "sample-plugin"
}
