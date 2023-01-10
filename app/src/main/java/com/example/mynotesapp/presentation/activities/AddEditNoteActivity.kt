package com.example.mynotesapp.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
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
import com.example.mynotesapp.databinding.ActivityAddEditNoteBinding
import com.example.mynotesapp.presentation.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var noteTitleEdt: EditText
    private lateinit var noteEdt: EditText
    private lateinit var saveBtn: Button
    private lateinit var cancelButton: Button
    private var noteID: Int = -1
    private val noteType by lazy { intent.getStringExtra("noteType") }
    private val viewModel: NoteViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()

        saveBtn.setOnClickListener {
            saveData() //update note
        }

        cancelButton.setOnClickListener{
            goBackToMainPage()
        }
    }

    private fun setupUI(){

        noteTitleEdt = binding.idEdtNoteName // edit text for the title
        noteEdt = binding.idEdtNoteDesc // edit text for the note description
        saveBtn = binding.idBtn // save button
        cancelButton = binding.idCancelButton

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
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveData(){

        val noteTitle = noteTitleEdt.text.toString()
        val noteDescription = noteEdt.text.toString()

        //Edit note
        if (noteType.equals("Edit")) {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val updatedNote = Note(noteTitle, noteDescription, getDateTime())
                updatedNote.id = noteID
                viewModel.updateNote(updatedNote)
                Toast.makeText(this, "Note Updated: $noteTitle",
                    Toast.LENGTH_LONG).show()
            }

            //Add new note
        } else {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {

                // if the string is not empty, add data to the database.
                viewModel.addNote(Note(noteTitle, noteDescription, getDateTime()))
                Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this, "Nothing added - Please add a title and description",
                    Toast.LENGTH_LONG).show()
            }
        }

        goBackToMainPage()
        this.finish()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(): String {
        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
        return sdf.format(Date())

    }

    private fun goBackToMainPage(){
        intent.putExtra("noteColor", Color.WHITE)
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }
}