package com.example.marvelappwitharchitecture.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelappwitharchitecture.data.Character
import com.example.marvelappwitharchitecture.data.CharactersRepository
import com.example.marvelappwitharchitecture.data.characters
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository: CharactersRepository = CharactersRepository()

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            delay(1000)
            state = UiState(loading = false, characters = repository.fetchCharacters())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character> = emptyList()
    )
}