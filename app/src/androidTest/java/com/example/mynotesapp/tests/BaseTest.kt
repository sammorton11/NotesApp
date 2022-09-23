package com.example.mynotesapp.tests

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.LayoutAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mynotesapp.R


interface BaseTest {


    fun clickButton(resourceId: Int){
        onView(withId(resourceId))
            .perform(click())
    }

    fun checkVisibility(resourceId: Int){
        onView(withId(resourceId))
            .check(matches(isDisplayed()))
    }

    fun typeInText(resourceId: Int, text: String){
        onView(withId(resourceId))
            .perform(typeText(text))
    }

    fun updateText(resourceId: Int, text: String){
        onView(withId(resourceId))
            .perform(replaceText(text))
    }

    fun checkTextVisibility(resourceId: Int, text: String){
        onView(withId(resourceId))
            .check(matches(withText(text)))
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