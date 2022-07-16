package com.example.mynotesapp.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

open class BaseTest {

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

    fun checkTextVisibility(text: Int){
        onView(withText(text))
            .check(matches(isDisplayed()))
    }
}