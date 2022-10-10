package com.example.mynotesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notesTable")
data class Note (

    @ColumnInfo(name = "title")
    val noteTitle: String,

    @ColumnInfo(name = "description")
    val noteDescription: String,

    @ColumnInfo(name = "timestamp")
    val timeStamp: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0

    companion object{

        //Sorting names ascending
        fun sortNames():Comparator<Note> = Comparator<Note> { o1, o2 ->
            o1!!.noteTitle.compareTo(o2!!.noteTitle)
        }

        fun sortNamesDescending():Comparator<Note> = Comparator<Note> { o1, o2 ->
            o2!!.noteTitle.compareTo(o1!!.noteTitle)
        }

        fun sortDates():Comparator<Note> = Comparator<Note> { o1, o2 ->
            o1!!.timeStamp.compareTo(o2!!.timeStamp)
        }

        fun sortDatesDescending():Comparator<Note> = Comparator<Note> { o1, o2 ->
            o2!!.timeStamp.compareTo(o1!!.timeStamp)
        }
    }
}