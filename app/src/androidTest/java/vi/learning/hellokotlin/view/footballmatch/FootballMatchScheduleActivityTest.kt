package vi.learning.hellokotlin.view.footballmatch

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import vi.learning.hellokotlin.R

/**
 * Created by taufiqotulfaidah on 11/23/18.
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FootballMatchScheduleActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(FootballMatchScheduleActivity::class.java)

    private fun delay(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testPrevAddMatchFavorite() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.rv_list_match))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_match))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorites_match)).perform(ViewActions.click())
    }

    @Test
    fun testPrevRemoveMatchFavorite() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.rv_list_match))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_match))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorites_match)).perform(ViewActions.click())
    }

    @Test
    fun testNextAddMatchFavorite() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_match))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.next_match)).perform(ViewActions.click())

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.rv_list_match))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_match))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorites_match)).perform(ViewActions.click())
    }

    @Test
    fun testNextRemoveMatchFavorite() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_match))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.next_match)).perform(ViewActions.click())

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.rv_list_match))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation_match))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorites_match)).perform(ViewActions.click())
    }
}