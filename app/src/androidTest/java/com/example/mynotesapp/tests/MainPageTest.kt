@file:Suppress("DEPRECATION")

package com.example.mynotesapp.tests

import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.example.mynotesapp.pages.MainPage
import com.example.mynotesapp.presentation.activities.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainPageTest : MainPage() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val testRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule(order = 2)
    val intentsRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
        add_note()
    }

    @After
    fun tearDown() {
        delete_note()
        Intents.release()

    }

    private val vAction = UIAction()
    private val vAssert = VisibilityView()

    private fun add_note(){
        vAction.add_note(
            titleTextDestination = titleEdit,
            titleText = testTitleText,
            descriptionTextDestination = descriptionEdit,
            descriptionText = testDescriptionText,
            saveButton = save
        )
    }

    private fun delete_note(){
        vAction.delete_note(mainRecyclerView, deleteIconButton, firstPosition)
    }

    @Test
    fun test_FAB(){
        vAssert.checkNoEllipsizedText(floatingActionButton)
        vAssert.checkNoOverlaps(floatingActionButton)
        vAssert.checkIsClickable(floatingActionButton)
        vAction.clickButton(floatingActionButton)
        pressBack()
    }

    @Test
    fun test_note_title_visibility(){
        vAssert.checkTextVisibility(title, testTitleText)
        vAssert.checkTextColor(title, colorWhite)
        vAssert.checkNoOverlaps(title)
        vAssert.checkNoEllipsizedText(title)
    }

    @Test
    fun test_date_visibility(){
        vAssert.checkVisibility(date)
        vAssert.checkTextColor(date, colorWhite)
        vAssert.checkNoOverlaps(date)
        vAssert.checkNoEllipsizedText(date)
    }

    @Test
    fun test_timer_button_visibility(){
        vAssert.checkVisibility(timerIconButton)
        vAssert.checkNoOverlaps(timerIconButton)
        vAssert.checkIsClickable(timerIconButton)
    }

    @Test
    fun test_click_timer_button_position(){
        vAction.clickItemAtPosition(mainRecyclerView, timerIconButton,0)
        pressBack()
    }

    @Test
    fun test_delete_button_visibility(){
        vAssert.checkVisibility(deleteIconButton)
        vAssert.checkNoOverlaps(deleteIconButton)
        vAssert.checkIsClickable(deleteIconButton)
    }

    @Test
    fun test_sorting_menu_button_visibility(){
        vAssert.checkVisibility(expandSortingMenuButton)
        vAssert.checkNoOverlaps(expandSortingMenuButton)
        vAssert.checkTextColor(expandSortingMenuButton, colorLightGreen)
        vAssert.checkIsClickable(expandSortingMenuButton)
        vAssert.checkNoEllipsizedText(expandSortingMenuButton)
        vAssert.checkNoOverlaps(expandSortingMenuButton)
        vAssert.checkIsClickable(expandSortingMenuButton)
    }

    @Test
    fun test_sorting_menu_button_actions(){
        vAction.clickButton(expandSortingMenuButton)
        // Todo: test radio group and radio buttons are visible after SM button click
        // Todo: click the sorting menu button again
        // Todo: assert that the radio group and radio buttons are not visible
    }

}