package com.example.marvelappwitharchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.marvelappwitharchitecture.ui.navigation.Navigation
import com.example.marvelappwitharchitecture.ui.theme.MarvelAppWithArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelAppWithArchitectureTheme {
                Navigation()
            }
        }
    }
}