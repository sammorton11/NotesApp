package com.example.mynotesapp.pages
import com.example.mynotesapp.R
import com.example.mynotesapp.tests.BaseTest

open class MainPage: BaseTest {

    fun getRecyclerView(): Int{
        return R.id.notesRV
    }

    fun getFAB(): Int{
        return R.id.idFAB
    }

    fun getWhiteTextColor(): Int{
        return R.color.white
    }

    fun getTimerIcon(): Int{
        return R.id.timerIcon
    }

    fun getDeleteIcon(): Int{
        return R.id.idIVDelete
    }

    fun getNoteTitle(): Int{
        return R.id.idTVNote
    }

    fun getNoteDateText(): Int{
        return R.id.idTVDate
    }


}