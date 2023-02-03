package com.bigtoapp.simplericktesttdd

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bigtoapp.simplericktesttdd.DrawableMatcher.withDrawableVector
import com.bigtoapp.simplericktesttdd.presentation.main.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    protected fun ViewInteraction.typeText(value: String) {
        perform(ViewActions.typeText(value))
        Espresso.closeSoftKeyboard()
    }

    protected fun ViewInteraction.checkText(value: String) {
        check(ViewAssertions.matches(ViewMatchers.withText(value)))
    }

    protected fun ViewInteraction.checkDrawable(value: Int) {
        check(ViewAssertions.matches(withDrawableVector(value)))
    }

    protected fun ViewInteraction.click() {
        perform(ViewActions.click())
    }

    protected fun Int.viewInRecycler(position: Int, viewId: Int): ViewInteraction =
        Espresso.onView(RecyclerViewMatcher(this).atPosition(position, viewId))
}