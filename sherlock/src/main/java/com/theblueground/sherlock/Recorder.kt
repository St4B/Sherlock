package com.theblueground.sherlock

import android.app.Activity

interface Recorder {

    fun record(screenshotName: String, activity: Activity, viewId: Int = 0)
}
