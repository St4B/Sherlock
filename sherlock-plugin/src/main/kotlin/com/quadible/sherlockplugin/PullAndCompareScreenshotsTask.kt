package com.quadible.sherlockplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

open class PullAndCompareScreenshotsTask : DefaultTask() {

    companion object {
        internal const val TASK_NAME_PULL_AND_COMPARE = "pullAndCompare"
    }

    @TaskAction
    fun pullAndCompare() = Unit
}

fun Project.registerPullAndCompareScreenshotsTask(
    pullScreenshotsTask: PullScreenshotsTask,
    compareScreenshotsTask: CompareScreenshotsTask
): PullAndCompareScreenshotsTask = tasks.create(
    PullAndCompareScreenshotsTask.TASK_NAME_PULL_AND_COMPARE,
    PullAndCompareScreenshotsTask::class.java
).apply {
    compareScreenshotsTask.setMustRunAfter(setOf(pullScreenshotsTask))
    dependsOn(pullScreenshotsTask, compareScreenshotsTask)
}
