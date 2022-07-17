package com.example.mynotesapp.pages
import com.example.mynotesapp.tests.BaseTest
import com.example.mynotesapp.R

class MainPage : BaseTest() {

    fun getFabButton(): Int {
        return R.id.idFAB
    }

    fun getRecyclerView(): Int {
        return R.id.notesRV
    }
}