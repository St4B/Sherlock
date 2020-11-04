package com.quadible.sherlockplugin

import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskProvider
import java.io.ByteArrayOutputStream

open class CompareScreenshotsTask : Exec() {

    companion object {
        internal const val TASK_NAME_COMPARE = "compareScreenshots"
    }

    @get:Internal
    internal val result: String
        get() = stdout.toString().trim()

    private val stdout = ByteArrayOutputStream()

    override fun exec() {
        standardOutput = stdout

        generate(
            code = CompareScreenshotsScript.getCode(),
            buildDir = project.buildDir,
            name = CompareScreenshotsScript.name
        )

        commandLine = listOf("sh", getCommand(name = CompareScreenshotsScript.name))
        super.exec()
    }
}

fun Project.registerCompareScreenshotsTask(): TaskProvider<CompareScreenshotsTask> = tasks.register(
    CompareScreenshotsTask.TASK_NAME_COMPARE,
    CompareScreenshotsTask::class.java
) { compareTask ->
    compareTask.doLast {
        println("Compare Screenshots result: ${compareTask.result}")
    }
}
