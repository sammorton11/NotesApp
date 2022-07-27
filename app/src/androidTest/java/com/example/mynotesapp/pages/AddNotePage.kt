package com.example.mynotesapp.pages

import com.example.mynotesapp.R
import com.example.mynotesapp.tests.BaseTest

class AddNotePage : Page() {

    fun getTitleEditField(): Int {
        return R.id.idEdtNoteName
    }

    fun getNoteDescriptionEditField(): Int {
        return R.id.idEdtNoteDesc
    }

    fun getSaveButton(): Int {
        return R.id.idBtn
    }

    fun getCancelButton(): Int {
        return R.id.idCancelButton
    }
}