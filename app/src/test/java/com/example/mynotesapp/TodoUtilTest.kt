package com.example.mynotesapp

import org.junit.Assert.assertFalse
import org.junit.Test

// test in progress
class TodoUtilTest {

    @Test
    fun empty_fields_return_false() {
        val result = TodoUtil.validateTodoInput(
            "",
            ""
        )
        assertFalse(result)
    }
}