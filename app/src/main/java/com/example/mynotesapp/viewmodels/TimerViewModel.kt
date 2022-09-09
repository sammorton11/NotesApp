package com.example.mynotesapp.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class Timer {
    //Should I be using a Constants class for these properties, or is this fine..?
    private var time_in_milli_seconds = 0L
    private var timeNumber: Long = 25L // initial time amount
    private lateinit var timer: CountDownTimer
    private var timerRunning: Boolean = false


    fun startTimer(timeTextView: TextView, startButton: Button, context: Context){

        time_in_milli_seconds = timeNumber * 60000L

        timer = object : CountDownTimer(time_in_milli_seconds, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                time_in_milli_seconds = millisUntilFinished
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

    fun stopTimer(startButton: Button){

        if (!timerRunning){
            return
        } else {
            timer.cancel()
            startButton.text = "Restart"
        }

    }

}