package com.example.mynotesapp

// Just practice

object TodoUtil {



    /**
     * the input is not valid if...
     * ...title and/or description is empty
     */
    fun validateTodoInput(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }


//    fun validateTodoInput2(note: Note): Boolean {
//        if(note.noteTitle.isEmpty() || note.noteDescription.isEmpty()){
//            return false
//        } else {
//            return true
//        }
//    }


}