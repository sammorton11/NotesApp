package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynotesapp.presentation.screens.main.MainActivity
import com.example.mynotesapp.pages.EditNotePage
import com.example.mynotesapp.util.Constants.UpdateButtonText
import com.example.mynotesapp.util.Constants.UpdateDescription
import com.example.mynotesapp.util.Constants.UpdateTitle
import com.example.mynotesapp.util.Constants.White
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EditNotePageTest: EditNotePage() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private val position: Int = 0
    private val titleEdit = getTitleEditField()
    private val descriptionEdit = getNoteDescriptionEditField()
    private val update = getUpdateButton()
    private val cancel = getCancelButton()

    //Title Edit
    @Test
    fun test_Title_Edit(){
        clickListItem(position)
        checkNoOverlaps(titleEdit)
        checkNoEllipsizedText(titleEdit)
        checkTextColor(titleEdit, White)
        clearText(titleEdit)
        updateText(titleEdit, UpdateTitle)
        clickButton(update)
       // checkTextVisibility(Constants.UpdateTitle) Todo: check main screen is visible
    }

    //Description Edit
    @Test
    fun test_Description_Edit(){
        clickListItem(position)
        checkNoOverlaps(descriptionEdit)
        checkNoEllipsizedText(descriptionEdit)
        checkTextColor(descriptionEdit, White)
        clearText(descriptionEdit)
        updateText(descriptionEdit, UpdateDescription)
        clickButton(update)
    }

    //Update Button
    @Test
    fun test_Update_Button(){
        clickListItem(position)
        checkNoOverlaps(update)
        checkNoEllipsizedText(update)
        checkTextVisibility(update, UpdateButtonText)
        checkTextColor(update, White)
        clickButton(update)
    }

    //Cancel Button
    @Test
    fun test_Cancel_Button(){
        clickListItem(position)
        clickButton(cancel)
       // checkTextVisibility(Constants.AppName) // Todo: check main screen is visible
    }
}