package ru.test.foodies.screens.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.dto.Product
import ru.test.foodies.model.ViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ProductCatalog(list: List<Product>) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(list) { product ->
            ProductCard(product)
        }
    }
}