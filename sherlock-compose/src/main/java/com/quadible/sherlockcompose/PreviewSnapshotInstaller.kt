package com.quadible.sherlockcompose

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.pm.ApplicationInfo
import android.database.Cursor
import android.net.Uri
import android.os.Debug
import android.util.Log

class PreviewSnapshotInstaller : ContentProvider() {

    override fun onCreate(): Boolean {
        if (context!!.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE == 0) {
            // Application is not debuggable. Not need to register any [ActivityLifecycleCallbacks]
            return false
        }

        (context as Application).registerActivityLifecycleCallbacks(ComposeRecorder())
        return false
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? = null

    override fun getType(p0: Uri): String? = null

    override fun insert(p0: Uri, p1: ContentValues?): Uri? = null

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int = 0

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int = 0
}
