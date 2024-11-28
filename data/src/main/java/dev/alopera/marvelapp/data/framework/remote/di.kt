package dev.alopera.marvelapp.data.framework.remote

import dagger.Binds
import dev.alopera.marvelapp.data.framework.CharacterRoomDataSource
import dev.alopera.marvelapp.data.framework.CharacterServerDataSource
import dev.alopera.marvelapp.domain.CharacterLocalDataSource
import dev.alopera.marvelapp.domain.CharacterRemoteDataSource

internal abstract class CharacterModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: CharacterRoomDataSource): CharacterLocalDataSource
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: CharacterServerDataSource): CharacterRemoteDataSource
}