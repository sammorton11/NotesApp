package com.example.mynotesapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.mynotesapp.data.entities.Note


interface NoteRepository {

    var allNotes: LiveData<List<Note>>

    suspend fun insert(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)

    suspend fun deleteAll()

}