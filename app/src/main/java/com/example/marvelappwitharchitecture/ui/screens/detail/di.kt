package com.example.marvelappwitharchitecture.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.marvelappwitharchitecture.ui.navigation.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @Named("characterId")
    fun provideCharacterId(savedStateHandle: SavedStateHandle): Int? {
        return savedStateHandle[NavArgs.CharacterId.key]
            ?: throw IllegalArgumentException(" CharacterId not found")
    }
}