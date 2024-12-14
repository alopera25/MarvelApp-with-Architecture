package com.example.marvelappwitharchitecture

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import dev.alopera.marvelapp.data.framework.database.CharacterDatabase

@HiltAndroidApp
class App : Application()