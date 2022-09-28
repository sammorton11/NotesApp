package com.example.mynotesapp.util

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.LayoutAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mynotesapp.R
import org.hamcrest.Matcher


abstract class BaseTest {


    class UIAction {

        private fun clickItemAtPosition(resourceId: Int) = object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on item at position in the RecyclerView"
            }

            override fun perform(uiController: UiController?, view: View?) {
                return click().perform(uiController, view?.findViewById(resourceId))
            }
        }

        fun clickItemAtPosition(recyclerView: Int, resourceId: Int, position: Int){
            onView(withId(recyclerView))
                .perform(
                    actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0, clickItemAtPosition(resourceId)
                    )
                )
        }

        fun clickButton(resourceId: Int){
            onView(withId(resourceId))
                .perform(click())
        }

        fun typeInText(resourceId: Int, text: String){
            onView(withId(resourceId))
                .perform(typeText(text))
        }

        fun updateText(resourceId: Int, text: String){
            onView(withId(resourceId))
                .perform(replaceText(text))
        }

        fun clickListItem(position: Int){
            onView(withId(R.id.notesRV))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>
                    (position, click())
                )
        }

        fun clearText(resourceId: Int){
            onView(withId(resourceId))
                .perform(clearText())
        }

        fun confirmDelete() {
            onView(withText("Delete"))
                .inRoot(isDialog()).perform(click())
        }
    }


    class VisibilityView{



        private fun checkItemAtPosition(resourceId: Int) = object : ViewAssertion {
            override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                TODO("Not yet implemented")
            }

        }



        // Visibility Tests
        fun checkVisibilityAtPosition(resourceId: Int, position: Int){
            onData(withId(resourceId))
                .atPosition(position)
                .check(matches(isDisplayed()))
        }

        fun checkTextVisibilityAtPosition(resourceId: Int, text: String, position: Int){
//            onView(withId(resourceId))
//                .perform(
//                    actionOnItemAtPosition<RecyclerView.ViewHolder>
//                        (position, scrollTo())
//                )
//                .check(matches(withText(text)))

            onData(withId(resourceId))
                .atPosition(position)
                .check(matches(withText(text)))
        }

        fun checkVisibility(resourceId: Int){
            onView(withId(resourceId))
                .check(matches(isDisplayed()))
        }

        fun checkTextVisibility(resourceId: Int, text: String){
            onView(withId(resourceId))
                .check(matches(withText(text)))
        }

        fun checkTextColorWhite(){
            onView(withId(R.color.white))
                .check(matches(isDisplayed()))
        }

        fun checkNoOverlaps(resourceId: Int){
            onView(withId(resourceId))
                .check(noOverlaps())
        }

        fun checkNoMultilineButtons(resourceId: Int){
            onView(withId(resourceId))
                .check(noMultilineButtons())
        }

        fun checkNoEllipsizedText(resourceId: Int){
            onView(withId(resourceId))
                .check(noEllipsizedText())
        }

        fun checkTextColor(resourceId: Int, colorResId: Int){
            onView(withId(resourceId))
                .check(matches(hasTextColor(colorResId)))
        }

        fun checkIsClickable(resourceId: Int){
            onView(withId(resourceId))
                .check(matches(isClickable()))
        }

        fun checkTextHintColor(colorResId: Int): BoundedMatcher<View?, TextView> {
            return object : BoundedMatcher<View?, TextView>(TextView::class.java){
                override fun matchesSafely(textView: TextView): Boolean {
                    val textViewColor = textView.currentHintTextColor
                    val expectedColor: Int = InstrumentationRegistry.getInstrumentation()
                        .targetContext
                        .getColor(colorResId)

                    return textViewColor == expectedColor
                }

                override fun describeTo(description: org.hamcrest.Description?) {
                    var colorId = colorResId.toString()
                    if (InstrumentationRegistry.getInstrumentation().targetContext != null) {
                        colorId = InstrumentationRegistry.getInstrumentation()
                            .targetContext
                            .resources
                            .getResourceName(colorResId)
                    }
                    description?.appendText("has color with ID $colorId")
                }
            }
        }

        fun checkTextColor(colorResId: Int): BoundedMatcher<View?, TextView> {
            return object : BoundedMatcher<View?, TextView>(TextView::class.java){
                override fun matchesSafely(textView: TextView): Boolean {
                    val textViewColor = textView.currentTextColor
                    val expectedColor: Int = InstrumentationRegistry.getInstrumentation()
                        .targetContext
                        .getColor(colorResId)

                    return textViewColor == expectedColor
                }

                override fun describeTo(description: org.hamcrest.Description?) {
                    var colorId = colorResId.toString()
                    if (InstrumentationRegistry.getInstrumentation().targetContext != null) {
                        colorId = InstrumentationRegistry.getInstrumentation()
                            .targetContext
                            .resources
                            .getResourceName(colorResId)
                    }
                    description?.appendText("has color with ID $colorId")
                    Log.d("COLOR ID","has color with ID $colorId")
                }
            }
        }
    }

}