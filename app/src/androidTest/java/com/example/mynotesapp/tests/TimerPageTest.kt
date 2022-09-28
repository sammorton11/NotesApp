package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mynotesapp.pages.TimerPage
import com.example.mynotesapp.presentation.screens.timer.TimerActivity
import com.example.mynotesapp.util.Constants.wait_for_timer
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

    private val actions = UIAction()
    private val viewTest = VisibilityView()
    private val _timerEditText = getTimerEditText()
    private val _minutesTextView = getMinutesTextView()
    private val _time = getTimeText()
    private val _increaseButton = getIncreaseButton()
    private val _decreaseButton = getDecreaseButton()
    private val _startButton = getStartButton()
    private val _stopButton = getStopButton()

    @Test
    fun test_timer_edit_text(){
        viewTest.checkVisibility(_timerEditText)
        viewTest.checkNoOverlaps(_timerEditText)
        viewTest.checkNoEllipsizedText(_timerEditText)
    }

    @Test
    fun test_minutes_text_view(){
        viewTest.checkVisibility(_minutesTextView)
        viewTest.checkNoOverlaps(_minutesTextView)
        viewTest.checkNoEllipsizedText(_minutesTextView)
        viewTest.checkTextVisibility(_minutesTextView, "minutes")
    }

    @Test
    fun test_increase_time_button(){
        viewTest.checkVisibility(_increaseButton)
        viewTest.checkNoOverlaps(_increaseButton)
        viewTest.checkNoEllipsizedText(_increaseButton)
        viewTest.checkNoMultilineButtons(_increaseButton)
        actions.clickButton(_increaseButton)
    }

    @Test
    fun test_decrease_time_button(){
        viewTest.checkVisibility(_decreaseButton)
        viewTest.checkNoOverlaps(_decreaseButton)
        viewTest.checkNoEllipsizedText(_decreaseButton)
        viewTest.checkNoMultilineButtons(_decreaseButton)
        actions.clickButton(_decreaseButton)
    }

    @Test
    fun test_time_text_view(){
        viewTest.checkVisibility(_time)
        viewTest.checkNoOverlaps(_time)
        viewTest.checkNoEllipsizedText(_time)
    }

    @Test
    fun test_start_button(){
        viewTest.checkVisibility(_startButton)
        viewTest.checkNoOverlaps(_startButton)
        viewTest.checkNoEllipsizedText(_startButton)
        viewTest.checkNoMultilineButtons(_startButton)
        actions.clickButton(_startButton)
        wait_for_timer()
        actions.clickButton(_stopButton)
        viewTest.checkTextVisibility(_time, ""+String.format("%d min : %d sec", 24, 54 ))
        viewTest.checkTextVisibility(_startButton, "Restart")
        actions.clickButton(_startButton)
    }

    @Test
    fun test_stop_button(){
        viewTest.checkVisibility(_stopButton)
        viewTest.checkNoOverlaps(_stopButton)
        viewTest.checkNoEllipsizedText(_stopButton)
        viewTest.checkNoMultilineButtons(_stopButton)
        actions.clickButton(_stopButton)
    }


}