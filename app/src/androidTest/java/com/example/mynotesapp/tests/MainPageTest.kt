package com.example.mynotesapp.tests

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.pages.MainPage
import com.example.mynotesapp.presentation.screens.main.MainActivity
import com.example.mynotesapp.util.Constants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Order

@HiltAndroidTest
class MainPageTest : MainPage() {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 2)
    val testRule = ActivityScenarioRule(MainActivity::class.java)

    private val vAction = UIAction()
    private val vAssert = VisibilityView()
    private val floatingActionButton = getFAB()
    private val timerIconButton = getTimerIcon()
    private val deleteIconButton = getDeleteIcon()
    private val mainRecyclerView = getRecyclerView()
    private val colorWhite = getWhiteTextColor()
    private val title = getNoteTitle()
    private val date = getNoteDateText()
    private val titleEdit = idEdtNoteName
    private val descEdit = idEdtNoteDesc
    private val save = idBtn

    @Test
    @Order(1)
    fun test_FAB_visibility(){
        vAssert.checkNoEllipsizedText(floatingActionButton)
        vAssert.checkNoOverlaps(floatingActionButton)
        vAssert.checkIsClickable(floatingActionButton)
    }
    @Test
    @Order(2)
    fun add_note(){
        vAction.clickButton(floatingActionButton)
        vAction.updateText(titleEdit, Constants.TestTitleText)
        vAction.updateText(descEdit, Constants.TestDescriptionText)
        vAction.clickButton(save)
    }

    @Test
    @Order(3)
    fun test_note_title_visibility(){
        vAssert.checkVisibilityAtPosition(title,0) // todo : this is broken
        vAssert.checkTextColor(title, colorWhite)
        vAssert.checkNoOverlaps(title)
        vAssert.checkNoEllipsizedText(title)
    }

    @Test
    @Order(4)
    fun test_date_visibility(){
        vAssert.checkVisibility(date)
        vAssert.checkTextColor(date, colorWhite)
        vAssert.checkNoOverlaps(date)
        vAssert.checkNoEllipsizedText(date)
    }

    @Test
    @Order(5)
    fun test_timer_button_visibility(){
        vAssert.checkVisibility(timerIconButton)
        vAssert.checkNoOverlaps(timerIconButton)
        vAssert.checkIsClickable(timerIconButton)
    }

    @Test
    @Order(6)
    fun test_click_timer_button_position(){
        vAction.clickItemAtPosition(mainRecyclerView, timerIconButton,0)
        pressBack()
    }

    @Test
    @Order(7)
    fun test_delete_button_visibility(){
        vAssert.checkVisibility(deleteIconButton)
        vAssert.checkNoOverlaps(deleteIconButton)
        vAssert.checkIsClickable(deleteIconButton)
    }

    @Test
    @Order(8)
    fun delete_note(){
        vAction.clickItemAtPosition(mainRecyclerView, deleteIconButton,0)
        vAction.confirmDelete()
    }

}