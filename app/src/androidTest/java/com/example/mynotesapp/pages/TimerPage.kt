package com.example.mynotesapp.pages

import com.example.mynotesapp.tests.BaseTest
import com.example.mynotesapp.R

open class TimerPage: BaseTest {

    fun getTimerEditText(): Int{
        return R.id.timerEditText
    }

    fun getMinutesTextView(): Int {
        return R.id.minutesTextView
    }

    fun getIncreaseButton(): Int {
        return R.id.upArrowButton
    }

    fun getDecreaseButton(): Int {
        return R.id.downArrowButton
    }

    fun getTimeText(): Int {
        return R.id.time
    }

    fun getStartButton(): Int {
        return R.id.startTimerButton
    }

    fun getStopButton(): Int {
        return R.id.stopButton
    }
}