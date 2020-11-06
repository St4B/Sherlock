package com.quadible.sherlockcompose

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.core.view.postDelayed
import androidx.ui.tooling.preview.PreviewActivity
import com.quadible.sherlock.ViewHierarchyRecorder
import java.lang.RuntimeException

/**
 * Takes a screenshot of Composable's preview.
 */
class ComposeRecorder : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) {
        // Preview happens only in PreviewActivity
        if (activity !is PreviewActivity) {
            return
        }

        // This code exists in [PreviewActivity] and is used in order to
        // render a composable. We duplicated the code here to get composable's
        // name use it as an image name.
        val composableName = activity.intent?.getStringExtra("composable")?.substringAfterLast('.')
            ?: throw RuntimeException("Missing composable name")

        // Give some time as compose rendering does
        // not seem to be bound on a lifecycle event
        activity.window.decorView.rootView.postDelayed(70L) {
            ViewHierarchyRecorder().record(screenshotName = composableName, activity = activity)
            activity.finish()
        }
    }

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}
