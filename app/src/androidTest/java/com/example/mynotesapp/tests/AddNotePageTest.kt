package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynotesapp.presentation.screens.add_edit.AddEditNoteActivity
import com.example.mynotesapp.pages.AddNotePage
import com.example.mynotesapp.util.Constants
import com.example.mynotesapp.util.Constants.SaveButtonText
import com.example.mynotesapp.util.Constants.TestDescriptionText
import com.example.mynotesapp.util.Constants.TestTitleText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNotePageTest: AddNotePage() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(AddEditNoteActivity::class.java)

    private val cancel = getCancelButton()
    private val titleEdit = getTitleEditField()
    private val descriptionEdit = getNoteDescriptionEditField()
    private val save = getSaveButton()


    // Title Edit Field
    @Test
    fun check_title_edit_visibility(){
        checkVisibility(getTitleEditField())
        checkNoOverlaps(getTitleEditField())
        checkNoEllipsizedText(getTitleEditField())
        checkTextHintColor(Constants.White)
        type_in_title_edit()
    }

    @Test
    fun type_in_title_edit(){
        typeInText(titleEdit, TestTitleText)
    }


    // Description edit field
    @Test
    fun check_description_edit_visibility(){
        checkVisibility(descriptionEdit)
        checkNoOverlaps(descriptionEdit)
        checkNoEllipsizedText(descriptionEdit)
    }

    @Test
    fun type_in_description_edit(){
        typeInText(descriptionEdit, TestDescriptionText)
    }

    // Save Button
    @Test
    fun check_save_button_visibility(){
        checkVisibility(save)
        checkTextVisibility(save, SaveButtonText)
        checkNoOverlaps(save)
    }

    @Test
    fun click_save_button(){
        clickButton(save)
        //Todo: check if something on the main screen is visible
    }

    // Cancel Button
    @Test
    fun check_cancel_button_visibility(){
        checkVisibility(cancel)
        checkNoOverlaps(cancel)
        checkNoEllipsizedText(cancel)
    }

    @Test
    fun click_cancel_button(){
        clickButton(cancel)
    }
}