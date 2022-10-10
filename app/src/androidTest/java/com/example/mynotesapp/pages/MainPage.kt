package com.example.mynotesapp.pages

import com.example.mynotesapp.R.color.white
import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BasePage

abstract class MainPage: BasePage() {

    val mainPageName = "Notes"
    val colorWhite = white
    val timerIconButton = timerIcon
    val title = idTVNote // note card title
    val date = idTVDate // note card date
    val descEdit = idEdtNoteDesc
}