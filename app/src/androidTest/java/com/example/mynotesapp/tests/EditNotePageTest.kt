package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import com.example.mynotesapp.activity.AddEditNoteActivity
import com.example.mynotesapp.activity.MainActivity
import com.example.mynotesapp.pages.AddNotePage
import com.example.mynotesapp.pages.EditNotePage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditNotePageTest: EditNotePage() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    val updateTitle = "Test_Title - Updated"
    val updateDescription = "Test_Description - Updated"
    val position: Int = 0
    val updateButtonText = "Update Note"

    @MediumTest
    fun test_EditNote(){
        clickListItem(position)
        clearText(getTitleEditField())
        clearText(getNoteDescriptionEditField())
        typeInText(getTitleEditField(), updateTitle)
        typeInText(getNoteDescriptionEditField(), updateDescription)
        checkTextVisibility(updateButtonText)
        clickButton(getUpdateButton())
        checkTextVisibility(updateTitle)
    }

    @SmallTest
    fun test_CancelAddNote(){
        clickListItem(position)
        clickButton(getCancelButton())
    }
}