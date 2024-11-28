package com.example.marvelappwitharchitecture.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.Result
import dev.alopera.marvelapp.domain.stateAsResultIn
import dev.alopera.marvelapp.usecases.FetchCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchCharactersUseCase: FetchCharactersUseCase) : ViewModel() {

    private val uiReady = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<Character>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchCharactersUseCase() }
        .stateAsResultIn(viewModelScope)
}