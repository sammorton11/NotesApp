package com.example.mynotesapp.presentation.activities.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.*
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.domain.adapters.*
import com.example.mynotesapp.presentation.activities.add_edit.AddEditNoteActivity
import com.example.mynotesapp.presentation.activities.timer.TimerActivity
import com.example.mynotesapp.presentation.viewmodels.NoteViewModel
import com.example.mynotesapp.util.Builders
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteClick, NoteClickDelete, NoteTimerClick {

    private val viewModel: NoteViewModel by viewModels()
    private val builders = Builders()
    private lateinit var clearButton: Button
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: Button

    private lateinit var sortByNameButton: RadioButton
    private lateinit var sortByDateButton: RadioButton
    private lateinit var sortAscending: RadioButton
    private lateinit var sortDescending: RadioButton

    private val redPinkColorIntent by lazy { intent.getStringExtra("noteColor") }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Notes"

        notesRV = findViewById(R.id.notesRV) // RecyclerView
        addFAB = findViewById(R.id.idFAB) // FloatingActionButton
        clearButton = findViewById(R.id.clearListButton)
        sortByNameButton = findViewById(R.id.sortByNameButton)
        sortByDateButton = findViewById(R.id.sortByDateButton)
        sortAscending = findViewById(R.id.sortAscending)
        sortDescending = findViewById(R.id.sortDescending)

        notesRV.layoutManager = LinearLayoutManager(this)

        observer()

        addFAB.setOnClickListener {
            openAddNotePage()
        }
    }

    private fun observer(){
        val noteRVAdapter = NoteRVAdapter(
            this, this, this, this
        )
        notesRV.adapter = noteRVAdapter
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(list)
                viewModel.sortList(
                    noteRVAdapter,
                    list,
                    sortByNameButton,
                    sortByDateButton,
                    sortAscending,
                    sortDescending
                )
            }

            clearButton.setOnClickListener {
                builders.deleteAllNotesAlertDialog(this){
                    viewModel.deleteAll(list)
                }
            }
        }
    }

    private fun openAddNotePage() {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        startActivity(intent)
    }

    //passing intent data when starting edit note activity
    private fun openEditNotePage(note: Note){
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }

    private fun openTimerPage(){
        val intent = Intent(this@MainActivity, TimerActivity::class.java)
        startActivity(intent)
    }

    override fun onNoteClick(note: Note) {
        openEditNotePage(note)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onTimerClick(note: Note) {
        openTimerPage()
    }
}