package com.example.mynotesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Allows hilt to know when the app has started and then initializes the dependency injection
@HiltAndroidApp
class NoteRoomApplication: Application()