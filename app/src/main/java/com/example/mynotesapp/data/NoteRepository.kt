package com.example.mynotesapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow



class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

}