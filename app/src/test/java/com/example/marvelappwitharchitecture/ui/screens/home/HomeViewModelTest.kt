package com.example.marvelappwitharchitecture.ui.screens.home

import dev.alopera.marvelapp.usecases.FetchCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchCharactersUseCase: FetchCharactersUseCase

    private lateinit var vm: HomeViewModel

    @Before
    fun setUp() {
        vm = HomeViewModel(fetchCharactersUseCase)
    }

    @Test
    fun `Characters are not requested if UI is not ready`() = runTest {
        vm.state.first()
        runCurrent()
        verify(fetchCharactersUseCase, times(0)).invoke()
    }

}