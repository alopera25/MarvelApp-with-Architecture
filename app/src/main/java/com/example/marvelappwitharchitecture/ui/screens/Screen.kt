package com.example.marvelappwitharchitecture.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.marvelappwitharchitecture.ui.theme.MarvelAppWithArchitectureTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    MarvelAppWithArchitectureTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}