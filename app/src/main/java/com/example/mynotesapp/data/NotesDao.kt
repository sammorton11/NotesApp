package com.example.mynotesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

//Simple Dao interface
@Dao
interface NotesDao {

    @Insert
    fun insert(note : Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    fun update(note: Note)

}