package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mynotesapp.pages.TimerPage
import com.example.mynotesapp.presentation.activities.timer.TimerActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TimerPageTest: TimerPage() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val testRule = ActivityScenarioRule(TimerActivity::class.java)

    private val vAction = UIAction()
    private val vAssert = VisibilityView()


    // Text to change time amount
    @Test
    fun test_timer_edit_text(){
        vAssert.checkVisibility(timerEdit)
        vAssert.checkNoOverlaps(timerEdit)
        vAssert.checkNoEllipsizedText(timerEdit)
    }

    // Minutes label
    @Test
    fun test_minutes_text_view(){
        vAssert.checkVisibility(minutesText)
        vAssert.checkNoOverlaps(minutesText)
        vAssert.checkNoEllipsizedText(minutesText)
        vAssert.checkTextVisibility(minutesText, "minutes")
    }

    // Increase time button
    @Test
    fun test_increase_button(){
        vAssert.checkVisibility(increaseButton)
        vAssert.checkNoOverlaps(increaseButton)
        vAssert.checkNoEllipsizedText(increaseButton)
        vAssert.checkNoMultilineButtons(increaseButton)
        vAction.clickButton(increaseButton)
    }

    // Decrease time button
    @Test
    fun test_decrease_button(){
        vAssert.checkVisibility(decreaseButton)
        vAssert.checkNoOverlaps(decreaseButton)
        vAssert.checkNoEllipsizedText(decreaseButton)
        vAssert.checkNoMultilineButtons(decreaseButton)
        vAction.clickButton(decreaseButton)
    }

    // Current time in timer text view
    @Test
    fun test_time_text_view(){
        vAssert.checkVisibility(timeText)
        vAssert.checkNoOverlaps(timeText)
        vAssert.checkNoEllipsizedText(timeText)
    }

    @Test
    fun test_start_button(){
        vAssert.checkVisibility(startTimer)
        vAssert.checkNoOverlaps(startTimer)
        vAssert.checkNoEllipsizedText(startTimer)
        vAssert.checkNoMultilineButtons(startTimer)
        vAction.clickButton(startTimer)
        wait_for_timer()
        vAction.clickButton(stopTimer)
        vAssert.checkTextVisibility(timeText, ""+String.format("%d min : %d sec", 24, 54 ))//  todo: redo this line
        vAssert.checkTextVisibility(startTimer, "Restart")
        vAction.clickButton(startTimer)
    }

    @Test
    fun test_stop_button(){
        vAssert.checkVisibility(stopTimer)
        vAssert.checkNoOverlaps(stopTimer)
        vAssert.checkNoEllipsizedText(stopTimer)
        vAssert.checkNoMultilineButtons(stopTimer)
        vAction.clickButton(stopTimer)
    }


}