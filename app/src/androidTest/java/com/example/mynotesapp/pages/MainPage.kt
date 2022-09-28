package com.example.mynotesapp.pages
import com.example.mynotesapp.R.color.white
import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BaseTest

open class MainPage: BaseTest() {

    fun getRecyclerView() = notesRV
    fun getFAB() = idFAB
    fun getWhiteTextColor() = white
    fun getTimerIcon() = timerIcon
    fun getDeleteIcon() = idIVDelete
    fun getNoteTitle() = idTVNote
    fun getNoteDateText() = idTVDate

}