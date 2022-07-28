package com.example.mynotesapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.*
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface, NoteTimerClickInterface {

    private lateinit var viewModel: NoteViewModel
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Notes"

        notesRV = findViewById(R.id.notesRV) // RecyclerView
        addFAB = findViewById(R.id.idFAB) // FloatingActionButton - opens task edit page
        notesRV.layoutManager = LinearLayoutManager(this)

        initializeViewModel()
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
                noteRVAdapter.updateList(it) // update list of notes with new list
            }
        }
    }

    //Should I be using a separate factory class to initialize my viewModels? instead of AndroidViewModelFactory? or is this fine?
    private fun initializeViewModel(){
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))[NoteViewModel::class.java]
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


    //Overriding interfaces from the NoteAdapter class
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