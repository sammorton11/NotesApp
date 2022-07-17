package com.example.mynotesapp.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.data.NoteDatabase
import com.example.mynotesapp.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NoteViewModel (application: Application) : AndroidViewModel(application)  {

    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes

    }


    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    private fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

    private fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }


    // Logic for saving data
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun saveData(noteTitleEdt: EditText, noteEdt: EditText, noteType: String?, noteID: Int, context: Context ){

        // title and description from edit text.
        val noteTitle = noteTitleEdt.text.toString()
        val noteDescription = noteEdt.text.toString()
        //Check the noteType
        //edit and update note
        if (noteType.equals("Edit")) {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                updatedNote.id = noteID
                updateNote(updatedNote)
                Toast.makeText(context, "Updated : $noteTitle", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error: Missing Title and Description", Toast.LENGTH_SHORT).show()
            }

            //add new note
        } else {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                // if the string is not empty, add data to the room database.
                addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                Toast.makeText(context, "Note added to list : $noteTitle", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error: Missing Title and Description", Toast.LENGTH_SHORT).show()
            }
        }
    }

}