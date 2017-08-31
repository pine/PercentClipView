package moe.pine.percent_clip_view

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import moe.pine.percent_clip_view.test.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test for ClipParams
 * Created by pine on 2017/06/06.
 */
@RunWith(AndroidJUnit4::class)
class ClipParamsTest {
    companion object {
        val DELTA = 0.0001f
    }

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule<Activity>(Activity::class.java)

    @Test
    fun constructorTest() {
        val activity = activityTestRule.activity
        val parent = activity.layoutInflater.inflate(R.layout.clip_view, null, false)
        val view = parent.findViewById<LinearLayout>(R.id.layout)

        Assert.assertEquals(view.clipTop, 0.125f, DELTA)
        Assert.assertEquals(view.clipRight, 0.250f, DELTA)
        Assert.assertEquals(view.clipBottom, 0.500f, DELTA)
        Assert.assertEquals(view.clipLeft, 0.750f, DELTA)
    }
}
