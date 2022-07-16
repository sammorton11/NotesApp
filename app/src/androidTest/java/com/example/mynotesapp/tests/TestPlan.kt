package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynotesapp.activity.MainActivity
import com.example.mynotesapp.pages.AddNotePage
import com.example.mynotesapp.pages.EditNotePage
import com.example.mynotesapp.pages.MainPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestPlan {

    private val mainPage = MainPage()
    private val addNotePage = AddNotePage()
    private val editNotePage = EditNotePage()

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickMainPageFabButton() {
        mainPage.clickButton(mainPage.getFabButton())
    }

    @Test
    fun checkMainFabVisibility(){
        mainPage.checkVisibility(mainPage.getFabButton())
    }

    fun clickCancelButton(){
        addNotePage.clickButton(addNotePage.getCancelButton())
    }

}