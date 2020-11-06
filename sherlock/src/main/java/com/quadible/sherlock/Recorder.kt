package com.quadible.sherlock

import android.app.Activity

interface Recorder {

    fun record(screenshotName: String, activity: Activity, viewId: Int = 0)
}
