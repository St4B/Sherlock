package com.quadible.sherlockplugin

import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskProvider
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Files

open class PullScreenshotsTask : Exec() {

    companion object {
        internal const val TASK_NAME_PULL = "pullScreenshots"
    }

    @get:Internal
    internal var applicationId = ""

    @get:Internal
    internal val result: String
        get() = stdout.toString().trim()

    private val stdout = ByteArrayOutputStream()

    override fun exec() {
        if (applicationId.isBlank()) {
            throw RuntimeException(
                "Application id is missing. Not possible to execute CompareScreenshotsTask"
            )
        }

        standardOutput = stdout

        val script = File("${project.buildDir}/sherlock-plugin", "pullScreenshots.sh")
        script.setExecutable(true)
        if (!script.exists()) {
            Files.createDirectories(script.parentFile.toPath())
            script.createNewFile()
            script.writeText(
                text = PullScreenshotsScript.getCode(applicationId = applicationId)
            )
        }

        commandLine = listOf("sh", "./build/sherlock-plugin/pullScreenshots.sh")
        super.exec()
    }
}

fun Project.registerPullScreenshotsTask(
    extension: SherlockPluginExtension
): TaskProvider<PullScreenshotsTask> = tasks.register(
    PullScreenshotsTask.TASK_NAME_PULL,
    PullScreenshotsTask::class.java
) { pullTask ->
    pullTask.doFirst {
        pullTask.applicationId = extension.applicationId
    }

    pullTask.doLast {
        println("Pull Screenshots result: ${pullTask.result}")
    }
}
