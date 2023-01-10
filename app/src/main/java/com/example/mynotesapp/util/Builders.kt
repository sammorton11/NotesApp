package com.example.mynotesapp.util

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.appcompat.app.AlertDialog
import com.example.mynotesapp.presentation.adapters.NoteClick
import com.example.mynotesapp.presentation.adapters.NoteClickDelete

class Builders {

    //Alert when delete button is pressed
    fun deleteNoteAlertDialog(
        context: Context,
        deleteNote: () -> Unit
    ){
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("Are you sure you want to delete this note?")
            .setCancelable(false)
            .setPositiveButton("Delete") { dialog, _ ->
                deleteNote() // delete note
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Delete this note?")
        alert.show()
    }

    fun deleteAllNotesAlertDialog(
        context: Context,
        deleteNotes: () -> Unit
    ){
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("Are you sure you want to delete all notes?")
            .setCancelable(false)
            .setPositiveButton("Delete") { dialog, _ ->
                deleteNotes() // delete note
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Delete all notes?")
        alert.show()
    }
}