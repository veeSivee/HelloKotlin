package vi.learning.hellokotlin.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vi.learning.hellokotlin.R

/**
 * Created by taufiqotulfaidah on 11/22/18.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAnkoFeature() {
        onView(withId(R.id.anko_feature)).check(matches(isDisplayed()))
        onView(withId(R.id.anko_feature)).perform(ViewActions.click())
    }

    @Test
    fun testOpenFootballClub() {
        onView(withId(R.id.football_club)).check(matches(isDisplayed()))
        onView(withId(R.id.football_club)).perform(ViewActions.click())
    }

    @Test
    fun testOpenFootballMatch() {
        onView(withId(R.id.football_match)).check(matches(isDisplayed()))
        onView(withId(R.id.football_match)).perform(ViewActions.click())
    }
}