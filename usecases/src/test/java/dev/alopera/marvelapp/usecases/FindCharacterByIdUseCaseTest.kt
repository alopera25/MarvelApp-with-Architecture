package dev.alopera.marvelapp.usecases

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindCharacterByIdUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        val characterFlow = flowOf(sampleCharacter(1))
        val useCase = FindCharacterByIdUseCase(mock {
            on { fetchCharacterById(1) } doReturn characterFlow
        })
        val result = useCase(1)
        assertEquals(characterFlow, result)
    }
}