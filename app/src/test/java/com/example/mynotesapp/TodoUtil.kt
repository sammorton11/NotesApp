package com.example.mynotesapp

object TodoUtil {
    fun validateTodoInput(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }
}