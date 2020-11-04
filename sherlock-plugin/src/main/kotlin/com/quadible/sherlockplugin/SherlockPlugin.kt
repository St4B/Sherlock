package com.quadible.sherlockplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SherlockPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.createSherlockPluginExtension()
        val getOSNameTask = project.registerGetOSNameTask()
        val checkFFMPEGTask = project.registerCheckFFMPEGTask()
        project.registerInstallFFMPEGTask(
            getOSNameTask = getOSNameTask,
            checkFFMPEGTask = checkFFMPEGTask
        )
        project.registerRecordPreviewsTask(extension = extension)
        project.registerPullScreenshotsTask(extension = extension)
        project.registerCompareScreenshotsTask()
    }
}
