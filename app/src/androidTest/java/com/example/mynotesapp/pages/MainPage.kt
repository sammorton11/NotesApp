package com.example.mynotesapp.pages

import com.example.mynotesapp.R.color.white
import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BasePage

abstract class MainPage: BasePage() {

    protected val mainPageName = "Notes"
    protected val title = idTVNote // note card title
    protected val date = idTVDate // note card date
    protected val descEdit = idEdtNoteDesc
}