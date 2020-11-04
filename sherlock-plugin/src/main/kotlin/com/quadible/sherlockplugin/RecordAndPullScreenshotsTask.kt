package com.quadible.sherlockplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

open class RecordAndPullScreenshotsTask : DefaultTask() {

    companion object {
        internal const val TASK_NAME_RECORD_AND_PULL = "recordAndPull"
    }

    @TaskAction
    fun pullAndCompare() = Unit
}

fun Project.registerRecordAndPullScreenshotsTask(
    recordPreviewsTask: RecordPreviewsTask,
    pullScreenshotsTask: PullScreenshotsTask
): RecordAndPullScreenshotsTask = tasks.create(
    RecordAndPullScreenshotsTask.TASK_NAME_RECORD_AND_PULL,
    RecordAndPullScreenshotsTask::class.java
).apply {
    pullScreenshotsTask.setMustRunAfter(setOf(recordPreviewsTask))
    dependsOn(recordPreviewsTask, pullScreenshotsTask)
}
