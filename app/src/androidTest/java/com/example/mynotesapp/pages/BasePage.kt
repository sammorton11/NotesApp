package com.example.mynotesapp.pages

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.mynotesapp.R
import io.mockk.verify
import org.junit.Test

open class Page {
    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }
    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        //page.verify()
        return page
    }
}