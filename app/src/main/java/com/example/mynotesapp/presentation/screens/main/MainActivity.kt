package com.example.mynotesapp.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.*
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.domain.adapters.NoteClickDeleteInterface
import com.example.mynotesapp.domain.adapters.NoteClickInterface
import com.example.mynotesapp.domain.adapters.NoteRVAdapter
import com.example.mynotesapp.domain.adapters.NoteTimerClickInterface
import com.example.mynotesapp.presentation.screens.add_edit.AddEditNoteActivity
import com.example.mynotesapp.presentation.screens.timer.TimerActivity
import com.example.mynotesapp.presentation.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    NoteClickInterface,
    NoteClickDeleteInterface,
    NoteTimerClickInterface {

    private val viewModel: NoteViewModel by viewModels()
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Notes"

        notesRV = findViewById(R.id.notesRV) // RecyclerView
        addFAB = findViewById(R.id.idFAB) // FloatingActionButton
        notesRV.layoutManager = LinearLayoutManager(this)
        observeAndUpdateData()

        addFAB.setOnClickListener {
            openAddNotePage()
        }

    }


    private fun observeAndUpdateData(){
        val noteRVAdapter = NoteRVAdapter(
            this, this, this, this
        )
        notesRV.adapter = noteRVAdapter
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
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