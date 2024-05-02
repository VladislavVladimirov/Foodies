package ru.test.foodies.screens.catalog.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.test.foodies.dto.Product

@Composable
fun ProductCatalog(
    list: List<Product>,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val listState = rememberLazyGridState()
    Box(Modifier.padding(paddingValues)) {
        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(list) { product ->
                ProductCard(product, navController)
            }
        }
    }

}