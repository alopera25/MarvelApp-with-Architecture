package com.example.marvelappwitharchitecture.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.Result
import dev.alopera.marvelapp.domain.stateAsResultIn
import dev.alopera.marvelapp.usecases.FetchCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class HomeViewModel(private val fetchCharactersUseCase: FetchCharactersUseCase) : ViewModel() {

    private val uiReady = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<Character>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchCharactersUseCase() }
        .stateAsResultIn(viewModelScope)
}