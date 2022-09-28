package com.example.mynotesapp.pages

import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BaseTest

abstract class EditNotePage : BaseTest() {
    fun getTitleEditField() = idEdtNoteName
    fun getNoteDescriptionEditField() = idEdtNoteDesc
    fun getUpdateButton() = idBtn
    fun getCancelButton() = idCancelButton
}
