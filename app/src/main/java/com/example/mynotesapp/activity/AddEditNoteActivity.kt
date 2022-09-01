package com.example.mynotesapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.R
import com.example.mynotesapp.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var noteTitleEdt: EditText
    private lateinit var noteEdt: EditText
    private lateinit var saveBtn: Button
    private lateinit var cancelButton: Button
    private var noteID: Int = -1
    private val noteType by lazy { intent.getStringExtra("noteType") }

    private val viewModel: NoteViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdt = findViewById(R.id.idEdtNoteName) // Title of Note
        noteEdt = findViewById(R.id.idEdtNoteDesc) // Note Description
        cancelButton = findViewById(R.id.idCancelButton)
        saveBtn = findViewById(R.id.idBtn)

        setupUI(noteTitleEdt, noteEdt, saveBtn)

        saveBtn.setOnClickListener {
            saveData()
        }

        cancelButton.setOnClickListener{
            goBackToMainPage()
        }
    }


    private fun setupUI(noteTitleEdt: EditText, noteEdt: EditText, saveBtn: Button){

        val noteTitle = intent.getStringExtra("noteTitle")
        val noteDescription = intent.getStringExtra("noteDescription")
        noteID = intent.getIntExtra("noteId", -1)

        viewModel.setupUI(noteTitleEdt, noteEdt, saveBtn, noteType, noteDescription, noteTitle)
    }


    //Update UI
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveData(){
        viewModel.saveData(noteTitleEdt, noteEdt, noteType, noteID, this)
        goBackToMainPage()
        this.finish()
    }

    private fun goBackToMainPage(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}