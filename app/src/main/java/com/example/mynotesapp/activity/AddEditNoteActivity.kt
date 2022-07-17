package com.example.mynotesapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.viewmodels.NoteViewModel
import com.example.mynotesapp.R

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    private var noteID: Int = -1

    private lateinit var saveBtn: Button
    private lateinit var cancelButton: Button
    private lateinit var viewModel: NoteViewModel
    private val noteType by lazy { intent.getStringExtra("noteType") }
    

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        initializeViewModel()
        setupUI()

        noteTitleEdt = findViewById(R.id.idEdtNoteName) // edit text for the title
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        cancelButton = findViewById(R.id.idCancelButton)

        saveBtn.setOnClickListener {
            saveData() //update note
        }

        cancelButton.setOnClickListener{
            goBackToMainPage()
        }
    }

    private fun initializeViewModel(){
        // initializing view modal.
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
    }

    private fun goBackToMainPage(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    private fun setupUI(){

        noteTitleEdt = findViewById(R.id.idEdtNoteName) // edit text for the title
        noteEdt = findViewById(R.id.idEdtNoteDesc) // edit text for the note description
        saveBtn = findViewById(R.id.idBtn) // save button
        cancelButton = findViewById(R.id.idCancelButton)

        // setting data to the edit text view
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            saveBtn.text = getString(R.string.update_note)
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
            supportActionBar?.setTitle(getString(R.string.update_note))

        } else {
            saveBtn.text = getString(R.string.save_note)
            supportActionBar?.title = getString(R.string.save_note)
        }
    }

    //Update UI
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveData(){
        viewModel.saveData(noteTitleEdt, noteEdt, noteType, noteID, this)
        goBackToMainPage()
        this.finish()
    }
}