package com.example.mynotesapp.di

import android.app.Application
import com.example.mynotesapp.data.NoteDatabase
import com.example.mynotesapp.data.NoteRepository
import com.example.mynotesapp.data.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NotesDao): NoteRepository {
        return NoteRepository(notesDao = noteDao)
    }
    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): NoteDatabase {
        return NoteDatabase.getDatabase(app)
    }
    @Singleton
    @Provides
    fun provideNoteDao(appDatabase: NoteDatabase): NotesDao {
        return appDatabase.getNotesDao()
    }

}