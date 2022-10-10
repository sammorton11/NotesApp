package com.example.mynotesapp.domain.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.data.entities.Note
import com.example.mynotesapp.util.Builders

class NoteRVAdapter(
    private val context: Context,
    private val noteClickDelete: NoteClickDelete,
    private val noteClick: NoteClick,
    private val noteTimerClick: NoteTimerClick
) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()
    private val builder: Builders = Builders()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)!! // note title in list item
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)!! // date label
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)!! // delete button
        val timerButton = itemView.findViewById<ImageView>(R.id.timerIcon)!! // timer icon button
        val noteCard = itemView.findViewById<CardView>(R.id.noteCard)!!

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // inflating layout file for each item of recycler view
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )

        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.noteTV.text = allNotes[position].noteTitle
        holder.dateTV.text = allNotes[position].timeStamp

        // opens delete dialog when delete icon is clicked
        holder.deleteIV.setOnClickListener {


            builder.deleteNoteAlertDialog(context) {
                noteClickDelete.onDeleteIconClick(allNotes[position])
            }
        }

        //click card at position in list
        holder.itemView.setOnClickListener {
            noteClick.onNoteClick(allNotes[position])
        }

        //opens timer page
        holder.timerButton.setOnClickListener {
            noteTimerClick.onTimerClick(allNotes[position])
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
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