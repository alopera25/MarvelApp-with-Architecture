package com.example.marvelappwitharchitecture.ui

import android.content.Context
import androidx.room.Room
import com.example.marvelappwitharchitecture.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alopera.marvelapp.data.framework.CharacterRoomDataSource
import dev.alopera.marvelapp.data.framework.CharacterServerDataSource
import dev.alopera.marvelapp.data.framework.database.CharacterDao
import dev.alopera.marvelapp.data.framework.database.CharacterDatabase
import dev.alopera.marvelapp.data.framework.remote.CharactersClient
import dev.alopera.marvelapp.data.framework.remote.CharactersService
import dev.alopera.marvelapp.domain.CharacterLocalDataSource
import dev.alopera.marvelapp.domain.CharacterRemoteDataSource
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

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context
    ): CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "character-db")
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db: CharacterDatabase
    ): CharacterDao = db.characterDao()
}

@Module
@InstallIn (ViewModelComponent::class)
abstract class CharacterModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: CharacterRoomDataSource): CharacterLocalDataSource
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: CharacterServerDataSource): CharacterRemoteDataSource
}

@Module
@InstallIn (SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideRetrofitService(): CharactersService {
        return CharactersClient.instance
    }
}