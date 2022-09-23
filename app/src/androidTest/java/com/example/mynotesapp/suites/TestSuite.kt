package com.example.mynotesapp.suites

import com.example.mynotesapp.tests.AddNotePageTest
import com.example.mynotesapp.tests.EditNotePageTest
import com.example.mynotesapp.tests.MainPageTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainPageTest::class,
    AddNotePageTest::class,
    EditNotePageTest::class
)
class TestSuite