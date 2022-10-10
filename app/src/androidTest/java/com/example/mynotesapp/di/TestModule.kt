package com.example.mynotesapp.di

import android.app.Application
import com.example.mynotesapp.data.dao.NotesDao
import com.example.mynotesapp.data.database.NoteDatabase
import com.example.mynotesapp.data.repository.NoteRepositoryImpl
import com.example.mynotesapp.domain.repository.NoteRepository
import com.example.mynotesapp.domain.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class TestModule {
    @Singleton
    @Provides
    fun bindNoteRepository(noteDao: NotesDao): NoteRepository {
        return NoteRepositoryImpl(notesDao = noteDao)
    }
    @Singleton
    @Provides
    fun bindAppDatabase(app: Application): NoteDatabase {
        return NoteDatabase.getDatabase(app)
    }
    @Singleton
    @Provides
    fun bindNoteDao(appDatabase: NoteDatabase): NotesDao {
        return appDatabase.getNotesDao()
    }
}