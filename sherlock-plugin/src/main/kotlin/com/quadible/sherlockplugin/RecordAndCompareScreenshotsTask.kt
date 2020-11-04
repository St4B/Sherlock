package com.quadible.sherlockplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

open class RecordAndCompareScreenshotsTask : DefaultTask() {

    companion object {
        internal const val TASK_NAME_RECORD_AND_COMPARE = "recordAndCompare"
    }

    @TaskAction
    fun pullAndCompare() = Unit
}

fun Project.registerRecordAndCompareScreenshotsTask(
    recordPreviewsTask: RecordPreviewsTask,
    pullScreenshotsTask: PullScreenshotsTask,
    compareScreenshotsTask: CompareScreenshotsTask
): RecordAndCompareScreenshotsTask = tasks.create(
    RecordAndCompareScreenshotsTask.TASK_NAME_RECORD_AND_COMPARE,
    RecordAndCompareScreenshotsTask::class.java
).apply {
    pullScreenshotsTask.setMustRunAfter(setOf(recordPreviewsTask))
    compareScreenshotsTask.setMustRunAfter(setOf(pullScreenshotsTask))
    dependsOn(recordPreviewsTask, pullScreenshotsTask, compareScreenshotsTask)
}
