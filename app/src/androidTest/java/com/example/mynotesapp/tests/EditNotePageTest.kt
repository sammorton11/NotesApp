package com.example.mynotesapp.tests

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mynotesapp.pages.EditNotePage
import com.example.mynotesapp.presentation.screens.main.MainActivity
import com.example.mynotesapp.util.Constants.UpdateDescription
import com.example.mynotesapp.util.Constants.UpdateTitle
import com.example.mynotesapp.util.Constants.White
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class EditNotePageTest: EditNotePage() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val testRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule(order = 2)
    val intentsRule = IntentsTestRule(MainActivity::class.java)

    private val actions = UIAction()
    private val viewTest = VisibilityView()
    private val position: Int = 0
    private val titleEdit = getTitleEditField()
    private val descriptionEdit = getNoteDescriptionEditField()
    private val update = getUpdateButton()
    private val cancel = getCancelButton()

    @Test
    fun test_intent(){
        intended(hasExtra("noteType", "Edit"))
    }

    //Title Edit
    @Test
    fun test_Title_Edit(){
        actions.clickButton(update)
        actions.clickListItem(position)
        viewTest.checkNoOverlaps(titleEdit)
        viewTest.checkNoEllipsizedText(titleEdit)
        viewTest.checkTextColor(titleEdit, White)
        actions.clearText(titleEdit)
        actions.updateText(titleEdit, UpdateTitle)
        actions.clickButton(update)
       // checkTextVisibility(Constants.UpdateTitle) Todo: check main screen is visible
    }

    //Description Edit
    @Test
    fun test_Description_Edit(){
//        actions.clickListItem(position)
        viewTest.checkNoOverlaps(descriptionEdit)
        viewTest.checkNoEllipsizedText(descriptionEdit)
        viewTest.checkTextColor(descriptionEdit, White)
        actions.clearText(descriptionEdit)
        actions.updateText(descriptionEdit, UpdateDescription)
        actions.clickButton(update)
    }

    //Update Button
    @Test
    fun test_Update_Button(){
        actions.clickListItem(position)
        viewTest.checkNoOverlaps(update)
        viewTest.checkNoEllipsizedText(update)
      //  viewTest.checkTextVisibility(update, UpdateButtonText)
        viewTest.checkTextColor(update, White)
        actions.clickButton(update)
    }

    @Test
    fun test_edit_note_intent(){
        actions.clickListItem(position)
        intended(hasExtra("noteType", "Edit"))
    }

    //Cancel Button
    @Test
    fun test_Cancel_Button(){
        actions.clickListItem(position)
        actions.clickButton(cancel)
       // checkTextVisibility(Constants.AppName) // Todo: check main screen is visible
    }
}