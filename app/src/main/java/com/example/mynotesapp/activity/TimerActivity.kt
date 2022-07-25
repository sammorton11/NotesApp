package com.example.mynotesapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mynotesapp.R

class TimerActivity : AppCompatActivity() {

    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var timer: CountDownTimer
    private lateinit var timeEdit: EditText
    private var time_in_milli_seconds = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timeTextView = findViewById(R.id.time)
        startButton = findViewById(R.id.startTimerButton)
        stopButton = findViewById(R.id.stopButton)
        timeEdit = findViewById(R.id.timerEditText)

        startButton.setOnClickListener {
            val time = timeEdit.text.toString()
            time_in_milli_seconds = time.toLong() *60000L
            startTimer(time_in_milli_seconds)
        }

        stopButton.setOnClickListener {
            stopTimer()
        }

        timeEdit.setOnClickListener {

        }

    }

    private fun startTimer(time_in_seconds: Long){
        startButton.text = "Start"

        // time count down for 30 seconds,
        // with 1 second as countDown interval
        timer = object : CountDownTimer(time_in_seconds, 1000) {

            // Callback function, fired on regular interval
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                time_in_milli_seconds = millisUntilFinished
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                timeTextView.text = ""+String.format("%d min : %d sec", minutes, seconds )
            }

            // Callback function, fired
            // when the time is up
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timeTextView.text = "Task session finished!"
                startButton.text = "Restart"
                Toast.makeText(applicationContext, "Timer Ended", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun stopTimer(){
        timer.cancel()
        startButton.text = "Restart"
    }

}