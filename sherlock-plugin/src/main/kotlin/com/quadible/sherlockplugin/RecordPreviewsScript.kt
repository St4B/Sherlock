package com.quadible.sherlockplugin

object RecordPreviewsScript {

    const val name: String = "recordPreviews.sh"

    fun getCode(composables: List<String>, applicationId: String): String =
        composables.fold("") { code, composableName ->
            code + getCodeForPreviewingComposable(composableName, applicationId)
        } + getCodeToIdle()

    private fun getCodeForPreviewingComposable(
        composableName: String,
        applicationId: String
    ): String = """
        while true
        do
            result="${'$'}(adb shell am start -n $applicationId/androidx.ui.tooling.preview.PreviewActivity -a android.intent.action.MAIN -c android.intent.category.LAUNCHER --es composable $composableName 2>&1 >/dev/null)"
            # If result is empty then record command was executed successfully. 
            if [[ -z "${'$'}result" ]]; then
                echo "Screenshot was take for preview $composableName"
                break
            fi
        done
        
        
    """.trimIndent()

    private fun getCodeToIdle(): String = """
        # Give some time to record the last screenshot before continuing to a next step
        sleep 0.5
    """.trimIndent()
}
