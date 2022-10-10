package com.example.mynotesapp.presentation.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Timer {
    //Should I be using a Constants class for these properties, or is this fine..?
    private var timeMilliseconds = 0L
    private var timeNumber: Long = 25L // initial time amount
    private lateinit var timer: CountDownTimer
    private var timerRunning: Boolean = false


    fun startTimer(timeTextView: TextView, startButton: Button, context: Context){

        timeMilliseconds = timeNumber * 60000L

        timer = object : CountDownTimer(timeMilliseconds, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timeMilliseconds = millisUntilFinished
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                timeTextView.text = ""+String.format("%d min : %d sec", minutes, seconds )
                timerRunning = true
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timeTextView.text = "Task session finished!"
                startButton.text = "Restart"
                Toast.makeText(context, "Timer Ended", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    fun increaseTime(timeEdit: TextView){
        timeNumber++
        timeEdit.text = timeNumber.toString()
    }

    fun decreaseTime(timeEdit: TextView){
        timeNumber--
        timeEdit.text = timeNumber.toString()
    }

    @SuppressLint("SetTextI18n")
    fun stopTimer(startButton: Button){

        if (!timerRunning){
            return
        } else {
            timer.cancel()
            startButton.text = "Restart"
        }

    }

}