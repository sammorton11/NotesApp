package com.example.mynotesapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotesapp.data.entities.Note


//Simple Dao interface
@Dao
interface NotesDao {

    @Insert
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    suspend fun update(note: Note)

    @Query("Delete from notesTable")
    suspend fun deleteAll()

}