package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mynotesapp.activity.AddEditNoteActivity
import com.example.mynotesapp.activity.MainActivity
import com.example.mynotesapp.pages.AddNotePage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNotePageTest: AddNotePage() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @MediumTest
    fun test_AddNote(){
        clickButton(getFabButton())
        checkVisibility(getTitleEditField())
        typeInText(getTitleEditField(), "Test_Title_01")
        checkVisibility(getNoteDescriptionEditField())
        typeInText(getNoteDescriptionEditField(), "Test_Description_01")
        checkTextVisibility("Save Note")
        clickButton(getSaveButton())

    }

    @SmallTest
    fun test_CancelAddNote(){
        clickButton(getCancelButton())
    }
}