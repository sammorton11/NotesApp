package com.example.mynotesapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.*
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    private lateinit var viewModel: NoteViewModel
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = "Notes"

        notesRV = findViewById(R.id.notesRV) // RecyclerView
        addFAB = findViewById(R.id.idFAB) // FloatingActionButton - opens task edit page
        notesRV.layoutManager = LinearLayoutManager(this)

        initializeViewModel()
        observeAndUpdateData()

        addFAB.setOnClickListener {
            openEditNotePage()
        }
    }


    private fun observeAndUpdateData(){
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        notesRV.adapter = noteRVAdapter

        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it) // update list of notes
            }
        }
    }

    private fun initializeViewModel(){
        // initializing view modal.
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
    }

    // page where you edit notes
    private fun openEditNotePage() {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    // function from NoteClick interfaces in the adapter class -- opens the note edit page
    override fun onNoteClick(note: Note) {
        // opening a new intent and passing data to it.
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }
}