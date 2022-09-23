package com.example.mynotesapp.tests

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.data.database.NoteDatabase
import com.example.mynotesapp.data.dao.NotesDao

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: NoteDatabase
    private lateinit var dao: NotesDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.getNotesDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    // not sure if this is a necessary test -- UI test covers this.
    @Test
    fun insertNoteItem() = runBlocking {
        val noteItem = Note("Title - UT","Description - UT", getDateTime())
        dao.insert(noteItem)

        val allNotes = dao.getAllNotes()

        //assertThat()

    }


    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(): String {
        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")

        return sdf.format(Date())
    }


}