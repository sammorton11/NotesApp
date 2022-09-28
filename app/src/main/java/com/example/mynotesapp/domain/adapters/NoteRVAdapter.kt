package com.example.mynotesapp.domain.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.R

class NoteRVAdapter(
    private val context: Context,
    private val noteClickDelete: NoteClickDelete,
    private val noteClick: NoteClick,
    private val noteTimerClick: NoteTimerClick
) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)!! // note title in list item
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)!! // date label
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)!! // delete button
        val timerButton = itemView.findViewById<ImageView>(R.id.timerIcon)!! // timer icon button
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating layout file for each item of recycler view
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.noteTV.text = allNotes[position].noteTitle
        holder.dateTV.text = "Date and Time : " + allNotes[position].timeStamp

        holder.deleteIV.setOnClickListener {
            deleteNoteAlertDialog(position)
        }

        holder.itemView.setOnClickListener {
            noteClick.onNoteClick(allNotes[position])
        }

        holder.timerButton.setOnClickListener {
            noteTimerClick.onTimerClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        allNotes.clear() // clear old list
        allNotes.addAll(newList) // update
        notifyDataSetChanged()
    }


    //Alert when delete button is pressed
    private fun deleteNoteAlertDialog(position: Int){
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("Are you sure you want to delete this note?")
            .setCancelable(false)
            .setPositiveButton("Delete") { _, _ ->
                noteClickDelete.onDeleteIconClick(allNotes[position]) // delete note
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Delete this note?")
        alert.show()
    }
}


interface NoteClickDelete{
    // creating a method for when the delete icon is clicked
    fun onDeleteIconClick(note: Note)
}

interface NoteClick {
    // creating a method for updating recycler view item
    fun onNoteClick(note: Note)
}

interface NoteTimerClick {
    fun onTimerClick(note: Note)
}