package com.example.mynotesapp.pages

import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BaseTest

abstract class TimerPage: BaseTest() {

    fun wait_for_timer() = Thread.sleep(5000)
    val timerEdit = timerEditText
    val minutesText = minutesTextView
    val increaseButton = upArrowButton
    val decreaseButton = downArrowButton
    val timeText = time
    val startTimer = startTimerButton
    val stopTimer = stopButton
}