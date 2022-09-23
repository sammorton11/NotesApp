package com.example.mynotesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.data.dao.NotesDao


open class NoteRepository(private val notesDao: NotesDao) {

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