@file:Suppress("DEPRECATION")

package com.example.mynotesapp.tests

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mynotesapp.pages.EditNotePage
import com.example.mynotesapp.presentation.activities.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Todo: Need to test the toolbar name - maybe check to see if back button exists

@HiltAndroidTest
class EditNotePageTest: EditNotePage() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val testRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule(order = 2)
    val intentsRule = IntentsTestRule(MainActivity::class.java)

    private val vAction = UIAction()
    private val vAssert = VisibilityView()

    @Before
    fun setUp(){
        vAction.add_note(
            titleTextDestination = titleEdit,
            titleText = testTitleText,
            descriptionTextDestination = descriptionEdit,
            descriptionText = testDescriptionText,
            saveButton = save
        )
        vAction.clickListItem(firstPosition)
        intended(hasExtra("noteType", "Edit"))
//        vAssert.isOnEditNotePage()
    }

    @After
    fun tear_down(){
//        vAssert.isOnMainPage()
        vAction.delete_note(mainRecyclerView, deleteIconButton, firstPosition)
    }

    //Title Edit
    @Test
    fun test_Title_Edit(){
        vAssert.checkVisibility(titleEdit)
        vAssert.checkNoOverlaps(titleEdit)
        vAssert.checkNoEllipsizedText(titleEdit)
        vAssert.checkTextColor(titleEdit, colorWhite)
        vAction.clearText(titleEdit)
        vAction.updateText(titleEdit, updateTitle)
        vAction.clickButton(update)
    }

    //Description Edit
    @Test
    fun test_Description_Edit(){
        vAssert.checkVisibility(descriptionEdit)
        vAssert.checkNoOverlaps(descriptionEdit)
        vAssert.checkNoEllipsizedText(descriptionEdit)
        vAssert.checkTextColor(descriptionEdit, colorWhite)
        vAction.clearText(descriptionEdit)
        vAction.updateText(descriptionEdit, updateDescription)
        vAction.clickButton(update)
    }

    //Update Button
    @Test
    fun test_Update_Button(){
        vAssert.checkNoOverlaps(update)
        vAssert.checkNoEllipsizedText(update)
        vAssert.checkTextVisibility(update, updateLabel)
        vAssert.checkTextColor(update, colorWhite)
        vAction.clickButton(update)
    }

    //Cancel Button
    @Test
    fun test_Cancel_Button(){
        vAction.clickButton(cancel)
    }
}