package vi.learning.hellokotlin.view.footballclub

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
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
class FootballclubFromApiActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(FootballclubFromApiActivity::class.java)

    private fun delay(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testAddFavorite() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.sprinner_team))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.sprinner_team)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("German Bundesliga")).perform(ViewActions.click())

        delay()
        Espresso.onView(ViewMatchers.withText("Ein Frankfurt"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Ein Frankfurt")).perform(ViewActions.click())

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorites)).perform(ViewActions.click())
    }

    @Test
    fun testRemoveFavorite() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.sprinner_team))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.sprinner_team)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("German Bundesliga")).perform(ViewActions.click())

        delay()
        Espresso.onView(ViewMatchers.withText("Ein Frankfurt"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Ein Frankfurt")).perform(ViewActions.click())

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorites)).perform(ViewActions.click())
    }
}