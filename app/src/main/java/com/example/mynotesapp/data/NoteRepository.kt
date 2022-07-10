package com.example.mynotesapp.data

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    fun insert(note: Note){
        notesDao.insert(note)
    }

    fun update(note: Note){
        notesDao.update(note)
    }

    fun delete(note: Note){
        notesDao.delete(note)
    }

}