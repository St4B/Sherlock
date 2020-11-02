package com.quadible.sherlockcomposeprocessor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import androidx.ui.tooling.preview.Preview
import java.io.File

/**
 * Tracks [Preview] annotation and adds a record for every annotation in a file.
 * These records will be used in order to execute a Gradle comparison task.
 */
class SherlockComposeProcessor : AbstractProcessor() {

    companion object {

        const val PREVIEWS_FILE_NAME = "previews.txt"

        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes() =
        mutableSetOf(Preview::class.java.canonicalName)

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        val kaptKotlinGeneratedDir =
            processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
                ?: return false

        roundEnv?.getElementsAnnotatedWith(Preview::class.java)?.forEach {
            val enclosingElement = it.enclosingElement
            val previewFunName = it.simpleName.toString()

            val sherlockCommands = File(kaptKotlinGeneratedDir, PREVIEWS_FILE_NAME)
            sherlockCommands.createNewFile()

            sherlockCommands.appendText("$enclosingElement.$previewFunName\n")
        }

        return true
    }
}
