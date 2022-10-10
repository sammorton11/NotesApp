@file:Suppress("DEPRECATION")

package com.example.mynotesapp.tests

import android.graphics.Color.WHITE
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.example.mynotesapp.pages.AddNotePage
import com.example.mynotesapp.presentation.activities.add_edit.AddEditNoteActivity
import com.example.mynotesapp.presentation.activities.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Todo: Need to test the toolbar name - maybe check to see if back button exists

@Suppress("DEPRECATION")
@HiltAndroidTest
class AddNotePageTest: AddNotePage() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val testRule = ActivityScenarioRule(AddEditNoteActivity::class.java)
    @get:Rule(order = 2)
    val intentsRule = ActivityTestRule(MainActivity::class.java)

    private val vAction = UIAction()
    private val vAssert = VisibilityView()

    @Before
    fun setUp(){
        Intents.init()
        vAction.clickButton(floatingActionButton)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    // Title Edit Field
    @Test
    fun check_title_edit_visibility(){
        vAssert.checkVisibility(titleEdit)
        vAssert.checkNoOverlaps(titleEdit)
        vAssert.checkNoEllipsizedText(titleEdit)
        vAssert.checkTextHintColor(WHITE)

    }

    @Test
    fun type_in_title_edit(){
        vAction.typeInText(titleEdit, testTitleText)
    }

    // Description edit field
    @Test
    fun check_description_edit_visibility(){
        vAssert.checkVisibility(descriptionEdit)
        vAssert.checkNoOverlaps(descriptionEdit)
        vAssert.checkNoEllipsizedText(descriptionEdit)
    }

    @Test
    fun type_in_description_edit(){
        vAction.typeInText(descriptionEdit, testDescriptionText)
    }

    // Save Button
    @Test
    fun check_save_button_visibility(){
        vAssert.checkVisibility(save)
        vAssert.checkTextVisibility(save, saveText)
        vAssert.checkNoOverlaps(save)
    }

    @Test
    fun click_save_button(){
        vAction.clickButton(save)
    }

    // Cancel Button
    @Test
    fun check_cancel_button_visibility(){
        vAssert.checkVisibility(cancel)
        vAssert.checkNoOverlaps(cancel)
        vAssert.checkNoEllipsizedText(cancel)
    }

    @Test
    fun click_cancel_button(){
        vAction.clickButton(cancel)
        vAssert.isOnMainPage()
    }
}