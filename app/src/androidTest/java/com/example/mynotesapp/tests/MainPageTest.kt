package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynotesapp.activity.MainActivity
import com.example.mynotesapp.pages.MainPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainPageTest : MainPage() {

    @get:Rule
    val testRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_FAB(){
        checkVisibility(getFabButton())
        clickButton(getFabButton())
    }
}