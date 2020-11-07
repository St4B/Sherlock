package com.quadible.sherlockplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.ByteArrayOutputStream
import java.io.File

open class RecordPreviewsTask : Exec() {

    companion object {
        internal const val TASK_NAME_RECORD_PREVIEWS = "recordPreviews"
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
                "Application id is missing. Not possible to execute RecordPreviewsTask"
            )
        }

        standardOutput = stdout


        val code = File(
            "${project.buildDir}/generated/source/kaptKotlin/debug",
            "previews.txt"
        ).useLines { composables ->
            RecordPreviewsScript.getCode(
                composables = composables.toList(),
                applicationId = applicationId
            )
        }


        generate(
            code = code,
            buildDir = project.buildDir,
            name = RecordPreviewsScript.name
        )

        commandLine = listOf("sh", getCommand(name = RecordPreviewsScript.name))
        super.exec()
    }
}

fun Project.registerRecordPreviewsTask(
    extension: SherlockPluginExtension
): RecordPreviewsTask = tasks.create(
    RecordPreviewsTask.TASK_NAME_RECORD_PREVIEWS,
    RecordPreviewsTask::class.java
) { recordTask ->
    recordTask.doFirst {
        recordTask.applicationId = extension.applicationId
    }
    recordTask.doLast {
        println("Pull Screenshots result: ${recordTask.result}")
    }
}
