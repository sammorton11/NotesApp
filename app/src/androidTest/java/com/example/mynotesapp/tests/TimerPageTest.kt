package com.example.mynotesapp.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mynotesapp.presentation.screens.timer.TimerActivity
import com.example.mynotesapp.pages.TimerPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerPageTest: TimerPage() {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(TimerActivity::class.java)

    private val _timerEditText = getTimerEditText()
    private val _minutesTextView = getMinutesTextView()
    private val _time = getTimeText()
    private val _increaseButton = getIncreaseButton()
    private val _decreaseButton = getDecreaseButton()
    private val _startButton = getStartButton()
    private val _stopButton = getStopButton()

    @Test
    fun test_timer_edit_text(){
        checkVisibility(_timerEditText)
        checkNoOverlaps(_timerEditText)
        checkNoEllipsizedText(_timerEditText)
    }

    @Test
    fun test_minutes_text_view(){
        checkVisibility(_minutesTextView)
        checkNoOverlaps(_minutesTextView)
        checkNoEllipsizedText(_minutesTextView)
        checkTextVisibility(_minutesTextView, "minutes")
    }

    @Test
    fun test_increase_time_button(){
        checkVisibility(_increaseButton)
        checkNoOverlaps(_increaseButton)
        checkNoEllipsizedText(_increaseButton)
        checkNoMultilineButtons(_increaseButton)
        clickButton(_increaseButton)
    }

    @Test
    fun test_decrease_time_button(){
        checkVisibility(_decreaseButton)
        checkNoOverlaps(_decreaseButton)
        checkNoEllipsizedText(_decreaseButton)
        checkNoMultilineButtons(_decreaseButton)
        clickButton(_decreaseButton)
    }

    @Test
    fun test_time_text_view(){
        checkVisibility(_time)
        checkNoOverlaps(_time)
        checkNoEllipsizedText(_time)
    }

    @Test
    fun test_start_button(){
        checkVisibility(_startButton)
        checkNoOverlaps(_startButton)
        checkNoEllipsizedText(_startButton)
        checkNoMultilineButtons(_startButton)
        clickButton(_startButton)

        Thread.sleep(5000)
        clickButton(_stopButton)
        Thread.sleep(5000)
        checkTextVisibility(_time, ""+String.format("%d min : %d sec", 24, 54 ))
        checkTextVisibility(_startButton, "Restart")
        clickButton(_startButton)
    }

    @Test
    fun test_stop_button(){
        checkVisibility(_stopButton)
        checkNoOverlaps(_stopButton)
        checkNoEllipsizedText(_stopButton)
        checkNoMultilineButtons(_stopButton)
        clickButton(_stopButton)
    }


}