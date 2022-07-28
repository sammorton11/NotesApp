package com.example.mynotesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.R
import com.example.mynotesapp.viewmodels.TimerViewModel

class TimerActivity : AppCompatActivity() {

    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var timeEdit: TextView
    private lateinit var upArrowButton: ImageButton
    private lateinit var downArrowButton: ImageButton
    private lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        title = "Timer"

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(application))[TimerViewModel::class.java]

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
            viewModel.stopTimer(startButton)
        }


        upArrowButton.setOnClickListener {
            viewModel.increaseTime(timeEdit)
        }

        downArrowButton.setOnClickListener {
            viewModel.decreaseTime(timeEdit)
        }

    }

    private fun initializeTimer() {

        //If the edit field is blank do nothing, else start the timer
        if(timeEdit.text.isNullOrBlank()){
            Toast.makeText(applicationContext, "Time Amount Edit Field is Blank", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            viewModel.startTimer(timeTextView, startButton, this)
        }
    }

}