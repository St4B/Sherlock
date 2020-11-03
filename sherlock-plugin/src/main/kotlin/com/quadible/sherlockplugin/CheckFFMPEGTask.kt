package com.quadible.sherlockplugin

import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskProvider
import java.io.ByteArrayOutputStream

open class CheckFFMPEGTask : Exec() {

    companion object {
        internal const val TASK_NAME_CHECK_FFMPEG_INSTALLATION= "checkFFMPEGInstallation"
    }

    @get:Internal
    internal val isInstalled: Boolean
        get() = stdout.toString().isNotEmpty()

    private val stdout = ByteArrayOutputStream()

    override fun exec() {
        standardOutput = stdout
        commandLine = listOf("ffmpeg", "-version")
        super.exec()
    }
}

fun Project.registerCheckFFMPEGTask(): TaskProvider<CheckFFMPEGTask> = tasks.register(
    CheckFFMPEGTask.TASK_NAME_CHECK_FFMPEG_INSTALLATION,
    CheckFFMPEGTask::class.java
) { checkFFMPEGTask ->
    checkFFMPEGTask.doLast {
        println("Is FFMPEG installed: ${checkFFMPEGTask.isInstalled}")
    }
}
