package com.quadible.sherlockplugin

import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskProvider
import java.io.ByteArrayOutputStream

open class GetOSNameTask : Exec() {

    companion object {
        internal const val TASK_NAME_GET_OPERATING_SYSTEM = "getOperatingSystem"
    }

    @get:Internal
    internal val osName: String
        get() = stdout.toString().trim()

    private val stdout = ByteArrayOutputStream()

    override fun exec() {
        standardOutput = stdout
        commandLine = listOf("uname", "-s")
        super.exec()
    }
}

fun Project.registerGetOSNameTask(): TaskProvider<GetOSNameTask> = tasks.register(
    GetOSNameTask.TASK_NAME_GET_OPERATING_SYSTEM,
    GetOSNameTask::class.java
) { getOsTask ->
    getOsTask.doLast {
        println("Operating System: ${getOsTask.osName}")
    }
}
