package com.example.mynotesapp.pages

import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BaseTest

open class AddNotePage : BaseTest() {
    fun getTitleEditField() = idEdtNoteName
    fun getNoteDescriptionEditField() = idEdtNoteDesc
    fun getSaveButton() = idBtn
    fun getCancelButton() = idCancelButton
}