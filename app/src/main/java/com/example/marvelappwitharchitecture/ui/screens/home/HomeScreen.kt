package com.example.marvelappwitharchitecture.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.marvelappwitharchitecture.R
import com.example.marvelappwitharchitecture.data.Character
import com.example.marvelappwitharchitecture.ui.common.LoadingIndicator
import com.example.marvelappwitharchitecture.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCharacterClick: (Character) -> Unit,
    vm: HomeViewModel = viewModel()
) {
    val homeState = rememberHomeState()

    Screen {
        val scrollBehavior = homeState.scrollBehavior
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    scrollBehavior = scrollBehavior,
                )
            },
            modifier = Modifier.nestedScroll(homeState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
        ) { padding ->
            val state by vm.state.collectAsState()

            if (state.loading) {
                LoadingIndicator()
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = padding,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                items(state.characters, key = { it.id }) {
                    CharacterItem(character = it) { onCharacterClick(it) }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = character.thumbnail,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}