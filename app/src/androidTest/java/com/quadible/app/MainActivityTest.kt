package com.quadible.app

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.quadible.sherlock.ViewHierarchyRecorder
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun a_test_that_takes_a_screenshot_of_the_whole_activity() {
        activityRule.scenario.onActivity {
            ViewHierarchyRecorder().record(screenshotName = "main_activity", activity = it)
        }
    }

    @Test
    fun a_test_that_takes_a_screenshot_of_a_specific_view() {
        activityRule.scenario.onActivity {
            ViewHierarchyRecorder().record(
                screenshotName = "main_activity_fab",
                activity = it,
                viewId = R.id.fab
            )
        }
    }
}
