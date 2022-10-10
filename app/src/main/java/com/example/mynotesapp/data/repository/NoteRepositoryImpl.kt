package com.example.mynotesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.mynotesapp.data.dao.NotesDao
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val notesDao: NotesDao): NoteRepository {

    override var allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    override suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    override suspend fun update(note: Note){
        notesDao.update(note)
    }

    override suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    override suspend fun deleteAll(note: List<Note>) {
        notesDao.deleteAll()
    }
}