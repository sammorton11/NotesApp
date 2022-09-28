package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mynotesapp.pages.AddNotePage
import com.example.mynotesapp.presentation.screens.add_edit.AddEditNoteActivity
import com.example.mynotesapp.util.Constants
import com.example.mynotesapp.util.Constants.SaveButtonText
import com.example.mynotesapp.util.Constants.TestDescriptionText
import com.example.mynotesapp.util.Constants.TestTitleText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AddNotePageTest: AddNotePage() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val testRule = ActivityScenarioRule(AddEditNoteActivity::class.java)

    private val actions = UIAction()
    private val viewTest = VisibilityView()
    private val cancel = getCancelButton()
    private val titleEdit = getTitleEditField()
    private val descriptionEdit = getNoteDescriptionEditField()
    private val save = getSaveButton()

    // Title Edit Field
    @Test
    fun check_title_edit_visibility(){
        //clickPosition(TestTitleText,0)
        viewTest.checkVisibility(getTitleEditField())
        viewTest.checkNoOverlaps(getTitleEditField())
        viewTest.checkNoEllipsizedText(getTitleEditField())
        viewTest.checkTextHintColor(Constants.White)

    }

    @Test
    fun type_in_title_edit(){
        actions.typeInText(titleEdit, TestTitleText)
    }

    // Description edit field
    @Test
    fun check_description_edit_visibility(){
        viewTest.checkVisibility(descriptionEdit)
        viewTest.checkNoOverlaps(descriptionEdit)
        viewTest.checkNoEllipsizedText(descriptionEdit)
    }

    @Test
    fun type_in_description_edit(){
        actions.typeInText(descriptionEdit, TestDescriptionText)
    }

    // Save Button
    @Test
    fun check_save_button_visibility(){
        viewTest.checkVisibility(save)
        viewTest.checkTextVisibility(save, SaveButtonText)
        viewTest.checkNoOverlaps(save)
    }

    @Test
    fun click_save_button(){
        actions.clickButton(save)
    }

    // Cancel Button
    @Test
    fun check_cancel_button_visibility(){
        viewTest.checkVisibility(cancel)
        viewTest.checkNoOverlaps(cancel)
        viewTest.checkNoEllipsizedText(cancel)
    }

    @Test
    fun click_cancel_button(){
        actions.clickButton(cancel)
    }
}