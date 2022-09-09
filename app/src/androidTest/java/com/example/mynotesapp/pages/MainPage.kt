package com.example.mynotesapp.pages
import com.example.mynotesapp.tests.BaseTest
import com.example.mynotesapp.R

open class MainPage : BaseTest() {

    fun getRecyclerView(): Int{
        return R.id.notesRV
    }

    fun getFAB(): Int{
        return R.id.idFAB
    }

}