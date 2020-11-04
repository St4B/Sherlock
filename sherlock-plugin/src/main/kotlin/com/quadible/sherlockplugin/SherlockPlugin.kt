package com.quadible.sherlockplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SherlockPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.createSherlockPluginExtension()
        val getOSNameTask = project.registerGetOSNameTask()
        val checkFFMPEGTask = project.registerCheckFFMPEGTask()
        val installFFMPEGTask = project.registerInstallFFMPEGTask(
            getOSNameTask = getOSNameTask,
            checkFFMPEGTask = checkFFMPEGTask
        )
        val recordPreviewsTask = project.registerRecordPreviewsTask(extension = extension)
        val pullScreenshotsTask = project.registerPullScreenshotsTask(extension = extension)
        val compareScreenshotsTask =
            project.registerCompareScreenshotsTask(installFFMPEGTask = installFFMPEGTask)

        project.registerRecordAndCompareScreenshotsTask(
            recordPreviewsTask = recordPreviewsTask,
            pullScreenshotsTask = pullScreenshotsTask,
            compareScreenshotsTask = compareScreenshotsTask
        )
    }
}
