package com.example.mynotesapp.presentation.screens.timer

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotesapp.R
import com.example.mynotesapp.presentation.viewmodels.Timer


class TimerActivity : AppCompatActivity() {

    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var timeEdit: TextView
    private lateinit var upArrowButton: ImageButton
    private lateinit var downArrowButton: ImageButton
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        title = "Timer"

        timeTextView = findViewById(R.id.time)
        startButton = findViewById(R.id.startTimerButton)
        stopButton = findViewById(R.id.stopButton)
        timeEdit = findViewById(R.id.timerEditText)
        upArrowButton = findViewById(R.id.upArrowButton)
        downArrowButton = findViewById(R.id.downArrowButton)

        startButton.setOnClickListener {
            initializeTimer()
        }

        stopButton.setOnClickListener {
            timer.stopTimer(startButton)
        }


        upArrowButton.setOnClickListener {
            timer.increaseTime(timeEdit)
        }

        downArrowButton.setOnClickListener {
            timer.decreaseTime(timeEdit)
        }

    }

    private fun initializeTimer() {
        //If the edit field is blank do nothing, else start the timer
        if(timeEdit.text.isNullOrBlank()){
            Toast.makeText(applicationContext, "Time Amount Edit Field is Blank", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            timer.startTimer(timeTextView, startButton, this)
        }
    }

}