package com.example.mynotesapp.pages

import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BaseTest

open class TimerPage: BaseTest() {
    fun getTimerEditText() = timerEditText
    fun getMinutesTextView() = minutesTextView
    fun getIncreaseButton() = upArrowButton
    fun getDecreaseButton() = downArrowButton
    fun getTimeText() = time
    fun getStartButton() = startTimerButton
    fun getStopButton() = stopButton
}