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
import com.example.mynotesapp.util.Constants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditNotePageTest: EditNotePage() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    val position: Int = 0

    @MediumTest
    fun test_EditNote(){
        clickListItem(position)
        clearText(getTitleEditField())
        clearText(getNoteDescriptionEditField())
        typeInText(getTitleEditField(), Constants.updateTitle)
        typeInText(getNoteDescriptionEditField(), Constants.updateDescription)
        checkTextVisibility(Constants.updateButtonText)
        clickButton(getUpdateButton())
        checkTextVisibility(Constants.updateTitle)
    }

    @SmallTest
    fun test_CancelAddNote(){
        clickListItem(position)
        clickButton(getCancelButton())
    }
}