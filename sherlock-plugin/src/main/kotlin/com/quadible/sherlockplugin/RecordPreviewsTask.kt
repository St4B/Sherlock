package com.quadible.sherlockplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider
import java.io.File

open class RecordPreviewsTask : DefaultTask() {

    companion object {
        internal const val TASK_NAME_RECORD_PREVIEWS = "recordPreviews"
    }

    @get:Internal
    internal var applicationId = ""

    @get:Internal
    internal val commands = mutableMapOf<String, List<String>>()

    @TaskAction
    fun record() {
        if (applicationId.isBlank()) {
            throw RuntimeException(
                "Application id is missing. Not possible to execute RecordPreviewsTask"
            )
        }

        File(
            "${project.buildDir}/generated/source/kaptKotlin/debug",
            "previews.txt"
        ).forEachLine { composableName ->
            val command = listOf(
                "adb", "shell", "am", "start", "-n",
                "$applicationId/androidx.ui.tooling.preview.PreviewActivity",
                "-a", "android.intent.action.MAIN", "-c", "android.intent.category.LAUNCHER",
                "--es composable $composableName"
            )
            commands[composableName] = command
        }
    }
}

fun Project.registerRecordPreviewsTask(
    extension: SherlockPluginExtension
): TaskProvider<RecordPreviewsTask> = tasks.register(
    RecordPreviewsTask.TASK_NAME_RECORD_PREVIEWS,
    RecordPreviewsTask::class.java
) { recordTask ->
    recordTask.doFirst {
        recordTask.applicationId = extension.applicationId
    }
    recordTask.doLast {
        recordTask.commands.forEach { (composableName, command) ->
            println("Taking screenshot for composable $composableName")
            project.exec { it.commandLine(command) }

            // We need to pause between every record in order to let device
            // render the composable. 200ms seem to work for now but in the
            // future we will need to find a way to remove Thread.sleep()
            Thread.sleep(200)
        }
    }
}
