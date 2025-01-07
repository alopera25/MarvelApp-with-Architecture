package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ToggleFavoriteUseCaseTest {
    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        val character = sampleCharacter(1)
        val repository = mock<CharacterRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(character)

        verify(repository).toggleFavorite(character)
    }
}