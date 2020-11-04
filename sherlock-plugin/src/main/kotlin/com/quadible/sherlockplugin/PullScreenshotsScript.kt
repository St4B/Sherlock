package com.quadible.sherlockplugin

object PullScreenshotsScript {

    const val name: String = "pullScreenshots.sh"

    fun getCode(applicationId: String): String = """
        # Add a directory in which we are going to pull the
        pullPath=${'$'}(pwd)"/pulled-screenshots"
        mkdir $`$`pullPath

        echo ${'$'}'\n*** Start pulling files from device ***'
        adb shell find "/storage/emulated/0/Android/data/$applicationId/files" -type f | while read file; do adb pull $`$`file $`$`pullPath; done
    """.trimIndent()

}
