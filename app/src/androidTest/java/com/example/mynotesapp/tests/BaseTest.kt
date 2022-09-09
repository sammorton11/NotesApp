package com.example.mynotesapp.tests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.mynotesapp.R


abstract class BaseTest {

    fun getFabButton(): Int {
        return R.id.idFAB
    }

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

    fun checkTextVisibility(text: String){
        onView(withText(text))
            .check(matches(isDisplayed()))
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


}