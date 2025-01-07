package dev.alopera.marvelapp.domain

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import sampleCharacter
import sampleCharacters

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest{

    @Mock
    lateinit var localDataSource: CharacterLocalDataSource

    @Mock
    lateinit var remoteDataSource: CharacterRemoteDataSource
    private lateinit var repository: CharacterRepository
    private val localCharacters = sampleCharacters(1, 2)
    private val remoteCharacters = sampleCharacters(3, 4)

    @Before
    fun setUp() {
        repository = CharacterRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `Characters are taken from local data source if available`() = runBlocking {
        whenever(localDataSource.character).thenReturn(flowOf(localCharacters))
        val result = repository.characters
        assertEquals(localCharacters, result.first())
    }

    @Test
    fun `Characters are saved to local data source when it's empty`(): Unit = runBlocking {
        whenever(localDataSource.character).thenReturn(flowOf(emptyList()))
        whenever(remoteDataSource.fetchCharacters(0,20)).thenReturn(remoteCharacters)
        repository.characters.first()
        verify(localDataSource).saveCharacter(remoteCharacters)
    }

    @Test
    fun `Finding a character by id is done in local data source`(): Unit = runBlocking {
        val character = sampleCharacter(5)
        whenever(localDataSource.fetchCharacterById(5)).thenReturn(flowOf(character))
        val result = repository.fetchCharacterById(5)
        assertEquals(character, result.first())
    }

    @Test
    fun `Toggling favorite updates local data source`(): Unit = runBlocking {
        val character = sampleCharacter(3)
        repository.toggleFavorite(character)
        verify(localDataSource).saveCharacter(argThat { get(0).id == 3 })
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite character`(): Unit = runBlocking {
        val character = sampleCharacter(1).copy(isFavorite = false)
        repository.toggleFavorite(character)
        verify(localDataSource).saveCharacter(argThat { get(0).isFavorite })
    }

    @Test
    fun `Switching favorite marks as unfavorite a favorite character`(): Unit = runBlocking {
        val character = sampleCharacter(1).copy(isFavorite = true)
        repository.toggleFavorite(character)
        verify(localDataSource).saveCharacter(argThat { !get(0).isFavorite })
    }

}
