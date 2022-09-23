package com.example.mynotesapp.tests

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynotesapp.presentation.screens.main.MainActivity
import com.example.mynotesapp.pages.MainPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainPageTest : MainPage() {

    private val floatingActionButton = getFAB()
    private val timerIconButton = getTimerIcon()
    private val deleteIconButton = getDeleteIcon()
    private val mainRecyclerView = getRecyclerView()
    private val colorWhite = getWhiteTextColor()
    private val title = getNoteTitle()
    private val date = getNoteDateText()

    @get:Rule
    val testRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_FAB(){
        checkNoEllipsizedText(floatingActionButton)
        checkNoOverlaps(floatingActionButton)
        clickButton(floatingActionButton)
        pressBack()
    }

    @Test
    fun test_note_card_title(){
        checkVisibility(title)
        checkTextColor(title, colorWhite)
        checkNoOverlaps(title)
        checkNoEllipsizedText(title)

    }

    @Test
    fun test_note_card_date(){
        checkVisibility(date)
        checkTextColor(date, colorWhite)
        checkNoOverlaps(date)
        checkNoEllipsizedText(date)

    }

    @Test
    fun test_TimerButton(){
        checkVisibility(timerIconButton)
        checkNoOverlaps(timerIconButton)
        clickButton(timerIconButton)
        pressBack()
        checkVisibility(timerIconButton)
    }

    @Test
    fun test_delete_button(){
        checkVisibility(deleteIconButton)
        clickButton(deleteIconButton)

    }

    @Test
    fun test_recycler_view(){
        checkVisibility(mainRecyclerView)
    }
}