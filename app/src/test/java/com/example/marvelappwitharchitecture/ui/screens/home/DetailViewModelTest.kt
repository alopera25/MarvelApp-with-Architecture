package com.example.marvelappwitharchitecture.ui.screens.home

import dev.alopera.marvelapp.domain.Result
import app.cash.turbine.test
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailViewModel
import dev.alopera.marvelapp.usecases.FindCharacterByIdUseCase
import dev.alopera.marvelapp.usecases.ToggleFavoriteUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findCharacterByIdUseCase: FindCharacterByIdUseCase

    @Mock
    lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var vm: DetailViewModel

    private val character = sampleCharacter(2)

    @Before
    fun setUp() {
        whenever(findCharacterByIdUseCase(2)).thenReturn(flowOf(character))
        vm = DetailViewModel(2, findCharacterByIdUseCase, toggleFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the character on start`() = runTest {
        vm.state.test {
                    assertEquals(Result.Loading, awaitItem())
                    assertEquals(Result.Success(character), awaitItem())
        }
    }

}
