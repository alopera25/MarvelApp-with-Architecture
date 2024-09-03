package com.example.marvelappwitharchitecture.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelappwitharchitecture.Result
import com.example.marvelappwitharchitecture.domain.Character
import com.example.marvelappwitharchitecture.ifSuccess
import com.example.marvelappwitharchitecture.stateAsResultIn
import com.example.marvelappwitharchitecture.usecases.FindCharacterByIdUseCase
import com.example.marvelappwitharchitecture.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
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