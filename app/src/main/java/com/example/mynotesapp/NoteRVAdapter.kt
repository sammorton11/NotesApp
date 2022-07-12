package com.example.mynotesapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.data.Note

class NoteRVAdapter(
    private val context: Context,
    private val noteClickDeleteInterface: NoteClickDeleteInterface,
    private val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {
    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)!! // note title in list item
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)!! // date label
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)!! // delete button
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
        // setting data to item in recycler view
        holder.noteTV.text = allNotes[position].noteTitle
        holder.dateTV.text = "Date and Time : " + allNotes[position].timeStamp

        holder.deleteIV.setOnClickListener {

            //Alert when delete button is pressed
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("Are you sure you want to delete this note?")
                .setCancelable(false)
                .setPositiveButton("Delete") { _, _ ->
                    noteClickDeleteInterface.onDeleteIconClick(allNotes[position]) // delete note
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Delete this note?")
            alert.show()

        }

        // click listener for recycler view item at certain index
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
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

}

interface NoteClickDeleteInterface {
    // creating a method for when the delete icon is clicked
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    // creating a method for updating recycler view item
    fun onNoteClick(note: Note)
}