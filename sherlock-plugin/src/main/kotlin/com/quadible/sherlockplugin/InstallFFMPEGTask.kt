package com.quadible.sherlockplugin

import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import java.io.ByteArrayOutputStream

open class InstallFFMPEGTask : Exec() {

    companion object {
        internal const val TASK_NAME_INSTALL_FFMPEG = "installFFMPEG"
    }

    @get:Internal
    internal var osName: String = ""

    @get:Internal
    internal val out: String
        get() = stdout.toString().trim()

    private val stdout = ByteArrayOutputStream()

    override fun exec() {
        val command = when (osName) {
            "Linux" -> listOf("sudo", "apt", "install", "ffmpeg")
            "Darwin" -> listOf("brew", "install", "ffmpeg")
            else -> throw RuntimeException("Not supported OS = $osName")
        }
        standardOutput = stdout
        commandLine = command
        super.exec()
    }
}

fun Project.registerInstallFFMPEGTask(
    getOSNameTask: GetOSNameTask,
    checkFFMPEGTask: CheckFFMPEGTask
): InstallFFMPEGTask = tasks.create(
    InstallFFMPEGTask.TASK_NAME_INSTALL_FFMPEG,
    InstallFFMPEGTask::class.java
) { installFFMPEGTask ->
    installFFMPEGTask.doFirst {
        installFFMPEGTask.osName = getOSNameTask.osName
    }
    installFFMPEGTask.doLast {
        println("FFMPEG was installed successfully")
    }
}.apply {
    onlyIf { !checkFFMPEGTask.isInstalled }
    dependsOn(getOSNameTask, checkFFMPEGTask)
}
