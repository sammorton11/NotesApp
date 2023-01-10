package com.example.mynotesapp.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.databinding.ActivityMainBinding
import com.example.mynotesapp.presentation.adapters.NoteClick
import com.example.mynotesapp.presentation.adapters.NoteClickDelete
import com.example.mynotesapp.presentation.adapters.NoteRVAdapter
import com.example.mynotesapp.presentation.viewmodels.NoteViewModel
import com.example.mynotesapp.util.Builders
import com.example.mynotesapp.util.Constants.babyBlue
import com.example.mynotesapp.util.Constants.redOrange
import com.example.mynotesapp.util.Constants.redPink
import com.example.mynotesapp.util.Constants.violet
import dagger.hilt.android.AndroidEntryPoint


/*
    Todo:
        - Redo Tests
        - Themes for Dark and Light settings
        - Need a new title
        - Change colors in Add Edit Note page
        - Need to get colors from res package for list of colors - throws NPE for some reason
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteClick, NoteClickDelete {

    private lateinit var notesRV: RecyclerView
    private val viewModel: NoteViewModel by viewModels()
    private val builders = Builders()
    private val colors = listOf(babyBlue, violet, redPink, redOrange)

    private var binding: ActivityMainBinding? = null
    private val _binding get() = binding!!

    private val noteRVAdapter = NoteRVAdapter(
        context = this,
        noteClickDelete = this,
        noteClick = this,
        colors = colors
    )
    
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        supportActionBar?.title = "To-Do List"

        notesRV = binding!!.notesRV
        notesRV.layoutManager = LinearLayoutManager(this)

        //LiveData observer
        observer()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.add_note -> {
                // Add note action
                openAddNotePage()
                true
            }
            R.id.clear -> {
                // Clear action
                deleteAllNotes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun observer(){
        notesRV.adapter = noteRVAdapter
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(list)
            }
        }
    }

    private fun deleteAllNotes() {
        builders.deleteAllNotesAlertDialog(this){
            viewModel.deleteAll()
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

    override fun onNoteClick(note: Note) {
        openEditNotePage(note)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}