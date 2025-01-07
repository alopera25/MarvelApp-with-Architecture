package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import org.junit.Test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchCharactersUseCaseTest{

    @Test
    fun `Invoke calls repository`() {
        val characterFlow = flowOf(sampleCharacters(1, 2))
        val useCase = FetchCharactersUseCase(mock {
            on { characters } doReturn characterFlow
        })
        val result = useCase()
        assertEquals(characterFlow, result)
    }
}