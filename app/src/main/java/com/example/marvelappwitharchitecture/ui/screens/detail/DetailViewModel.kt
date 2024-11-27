package com.example.marvelappwitharchitecture.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.Result
import dev.alopera.marvelapp.domain.ifSuccess
import dev.alopera.marvelapp.domain.stateAsResultIn
import dev.alopera.marvelapp.usecases.FindCharacterByIdUseCase
import dev.alopera.marvelapp.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    id: Int,
    findMovieByIdUseCase: FindCharacterByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<Result<Character>> = findMovieByIdUseCase(id)
        .stateAsResultIn(scope = viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess {
            viewModelScope.launch {
                toggleFavoriteUseCase(it)
            }
        }
    }

}