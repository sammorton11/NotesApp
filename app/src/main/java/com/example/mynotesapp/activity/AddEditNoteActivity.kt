package com.example.mynotesapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.viewmodels.NoteViewModel
import com.example.mynotesapp.R
import com.example.mynotesapp.data.Note
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var noteTitleEdt: EditText
    private lateinit var noteEdt: EditText
    private lateinit var saveBtn: Button
    private lateinit var cancelButton: Button
    private lateinit var viewModel: NoteViewModel
    private val noteType by lazy { intent.getStringExtra("noteType") } // getting data passed via an intent.
    private var noteID: Int = -1

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        initializeViewModel()
        setupUI()

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

    private fun setupUI(){

        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)
        cancelButton = findViewById(R.id.idCancelButton)

        if (noteType.equals("Edit")) {
            // setting data to the edit text view
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            saveBtn.text = getString(R.string.update_note)
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
            supportActionBar?.setTitle(getString(R.string.update_note))

        } else {
            saveBtn.text = getString(R.string.save_note)
            supportActionBar?.setTitle(getString(R.string.save_note))
        }
    }

    private fun goBackToMainPage(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }


    //Update UI
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveData(){

        // title and description from edit text.
        val noteTitle = noteTitleEdt.text.toString()
        val noteDescription = noteEdt.text.toString()

        //Check the noteType
        //edit note
        if (noteType.equals("Edit")) {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                updatedNote.id = noteID
                viewModel.updateNote(updatedNote)
                Toast.makeText(this, "Note Updated: $noteTitle", Toast.LENGTH_LONG).show()
            }

        //add new note
        } else {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                // if the string is not empty, add data to the room database.
                viewModel.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Nothing added - Please add a title and description", Toast.LENGTH_LONG).show()
            }
        }

        // opening the main page
        goBackToMainPage()
        this.finish()
    }
}