package com.example.mynotesapp.pages

import com.example.mynotesapp.R
import com.example.mynotesapp.tests.BaseTest

class EditNotePage : BaseTest() {

    fun getTitleEditField(): Int {
        return R.id.idEdtNoteName
    }

    fun getNoteDescriptionEditField(): Int {
        return R.id.idEdtNoteDesc
    }

    fun getUpdateButton(): Int {
        return R.id.idBtn
    }

    fun getCancelButton(): Int {
        return R.id.idCancelButton
    }
}