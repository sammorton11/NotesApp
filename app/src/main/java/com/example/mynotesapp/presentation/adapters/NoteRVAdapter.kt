package com.example.mynotesapp.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
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
    private val colors: List<Int>
) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()
    private val builder: Builders = Builders() // needed for alert dialogs

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)!! // note title in list item
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)!! // date&time label
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)!! // delete button
        val descriptionText = itemView.findViewById<TextView>(R.id.description)!!
        val noteCard = itemView.findViewById<CardView>(R.id.noteCard)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )

        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.noteTV.text = allNotes[position].noteTitle
        holder.descriptionText.text = allNotes[position].noteDescription
        holder.dateTV.text = allNotes[position].timeStamp
        holder.noteCard.setCardBackgroundColor(colors[position % colors.size])

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
    // function for when the delete icon is clicked
    fun onDeleteIconClick(note: Note)
}

interface NoteClick {
    // function for clicking note card items
    fun onNoteClick(note: Note)
}