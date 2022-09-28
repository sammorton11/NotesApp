package com.example.mynotesapp.presentation.screens.add_edit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotesapp.R
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.presentation.screens.main.MainActivity
import com.example.mynotesapp.presentation.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


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
        setupUI()

        saveBtn.setOnClickListener {
            saveData() //update note
        }

        cancelButton.setOnClickListener{
            goBackToMainPage()
        }
    }



    private fun setupUI(){

        noteTitleEdt = findViewById(R.id.idEdtNoteName) // edit text for the title
        noteEdt = findViewById(R.id.idEdtNoteDesc) // edit text for the note description
        saveBtn = findViewById(R.id.idBtn) // save button
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



    //Update UI
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveData(){

        val noteTitle = noteTitleEdt.text.toString()
        val noteDescription = noteEdt.text.toString()

        //Edit note
        if (noteType.equals("Edit")) {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val updatedNote = Note(noteTitle, noteDescription, getDateTime())
                updatedNote.id = noteID
                viewModel.updateNote(updatedNote)
                Toast.makeText(this, "Note Updated: $noteTitle", Toast.LENGTH_LONG).show()
            }

            //Add new note
        } else {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {

                // if the string is not empty, add data to the database.
                viewModel.addNote(Note(noteTitle, noteDescription, getDateTime()))
                Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Nothing added - Please add a title and description", Toast.LENGTH_LONG).show()
            }
        }

        goBackToMainPage()
        this.finish()
    }


    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(): String {
        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
        return sdf.format(Date())
    }

    private fun goBackToMainPage(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }
}