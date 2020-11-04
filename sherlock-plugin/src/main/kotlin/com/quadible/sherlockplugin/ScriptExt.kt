package com.quadible.sherlockplugin

import java.io.File
import java.nio.file.Files

//https://youtrack.jetbrains.com/issue/KT-2425
val `$` = "$"

fun generate(code: String, buildDir: File, name: String) {
    val script = File("$buildDir/sherlock-plugin", name)
    script.setExecutable(true)
    if (!script.exists()) {
        Files.createDirectories(script.parentFile.toPath())
        script.createNewFile()
        script.writeText(text = code)
    }
}

fun getCommand(name: String): String = "./build/sherlock-plugin/$name"
