package com.example.marvelappwitharchitecture.ui

import com.example.marvelappwitharchitecture.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey() = BuildConfig.MARVEL_API_KEY
}