package com.example.mynotesapp.presentation.viewmodels

import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.data.repository.NoteRepositoryImpl
import com.example.mynotesapp.domain.adapters.NoteRVAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel
@Inject constructor(private val noteRepository: NoteRepositoryImpl) : ViewModel() {

    val allNotes: LiveData<List<Note>> = noteRepository.allNotes

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun deleteAll(note: List<Note>) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteAll(note)
    }

    fun sortList(
        adapter: NoteRVAdapter,
        list: List<Note>,
        sortByNameButton: RadioButton,
        sortByDateButton: RadioButton,
        Ascending: RadioButton,
        Descending: RadioButton
    ){
        sortByNameButton.setOnClickListener {

            if (Ascending.isChecked){
                Collections.sort(list, Note.sortNames())
                adapter.updateList(list)
            }
            else if (Descending.isChecked){
                Collections.sort(list, Note.sortNamesDescending())
                adapter.updateList(list)
            }

        }

        sortByDateButton.setOnClickListener {
            if (Ascending.isChecked){
                Collections.sort(list, Note.sortDates())
                adapter.updateList(list)
            }
            else if (Descending.isChecked){
                Collections.sort(list, Note.sortDatesDescending())
                adapter.updateList(list)
            }
        }

    }

}

