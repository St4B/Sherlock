package com.quadible.sherlock

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Handler
import android.view.PixelCopy
import java.io.File
import java.io.FileOutputStream

class ViewHierarchyRecorder : Recorder {

    companion object {
        private const val PNG_EXTENSION = ".png"
    }

    override fun record(screenshotName: String, activity: Activity, viewId: Int) {
        val rootView = activity.window.decorView.rootView

        val view = if (viewId > 0) {
            rootView.findViewById(viewId)
        } else {
            rootView
        }

        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        val startingPoints = IntArray(2)
        view.getLocationOnScreen(startingPoints)
        val rect = Rect(
            startingPoints[0],
            startingPoints[1],
            startingPoints[0] + bitmap.width,
            startingPoints[1] + bitmap.height
        )

        // We can use drawing cache to take screenshot for SDKs < 26.
        // Possibly we'll add it to the future
        PixelCopy.request(activity.window, rect, bitmap, { copyResult ->
            if (copyResult == PixelCopy.SUCCESS) {
                val screenshot =
                    File(activity.getExternalFilesDir(null), screenshotName + PNG_EXTENSION)

                FileOutputStream(screenshot).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                }
            }
        }, Handler())
    }
}
