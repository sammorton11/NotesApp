package com.example.mynotesapp.util

import com.example.mynotesapp.R

object Constants {

    // Test Strings
    const val TestTitleText = "Test_Title_01"
    const val TestDescriptionText = "Test_Description_01"
    const val SaveButtonText = "Save Note"
    const val UpdateTitle = "Test_Title - Updated"
    const val UpdateDescription = "Test_Description - Updated"
    const val UpdateButtonText = "Update Note"
    const val AppName = "Notes"

    //Colors
    const val White: Int = R.color.white
    const val Navy_Blue: Int = R.color.purple_200

    fun wait_for_timer() = Thread.sleep(5000)
}