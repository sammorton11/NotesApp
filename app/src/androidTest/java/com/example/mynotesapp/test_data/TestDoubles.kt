package com.example.mynotesapp.test_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.domain.repository.NoteRepository


// Todo: Idk what I'm doing
object FakeNoteRepository : NoteRepository {
    private val FakeNote1 = Note("1","1", "1")
    private val FakeNote2 = Note("2","2", "2")
    private val FakeList: LiveData<List<Note>> = liveData { listOf(FakeNote1, FakeNote2) }

    override var allNotes: LiveData<List<Note>> = FakeList


    override suspend fun insert(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}

