package com.example.mynotesapp

import org.junit.Assert.assertFalse
import org.junit.Test

class TodoUtilTest {

    @Test
    fun empty_fields_return_false() {
        val result = TodoUtil.validateTodoInput(
            "",
            ""
        )

        assertFalse(result)
    }

    @Test
    fun valid_fields_return_true2() {
//        val result = TodoUtil.validateTodoInput(
//
//        )
//
//        assertTrue(result)
    }

}