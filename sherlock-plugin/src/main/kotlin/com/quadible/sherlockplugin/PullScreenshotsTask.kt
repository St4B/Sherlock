package com.quadible.sherlockplugin

import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import java.io.ByteArrayOutputStream

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

        generate(
            code = PullScreenshotsScript.getCode(applicationId = applicationId),
            buildDir = project.buildDir,
            name = PullScreenshotsScript.name
        )

        commandLine = listOf("sh", getCommand(name = PullScreenshotsScript.name))
        super.exec()
    }
}

fun Project.registerPullScreenshotsTask(
    extension: SherlockPluginExtension
): PullScreenshotsTask = tasks.create(
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
