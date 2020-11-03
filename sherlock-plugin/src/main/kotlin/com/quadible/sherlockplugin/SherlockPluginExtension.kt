package com.quadible.sherlockplugin

import org.gradle.api.Project

open class SherlockPluginExtension {

    var moduleName: String = ""

    var applicationId: String = ""
}

private const val EXTENSION_NAME = "sherlock"

fun Project.createSherlockPluginExtension(): SherlockPluginExtension = extensions.create(
    EXTENSION_NAME,
    SherlockPluginExtension::class.java
)
