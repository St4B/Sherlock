package com.quadible.sherlockplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SherlockPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.createSherlockPluginExtension()
        project.registerGetOSNameTask()
        project.registerCheckFFMPEGTask()
        project.registerRecordPreviewsTask(extension = extension)
    }
}
