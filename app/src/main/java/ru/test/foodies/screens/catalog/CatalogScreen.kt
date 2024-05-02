package ru.test.foodies.screens.catalog


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.model.Model
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.composables.BottomBar
import ru.test.foodies.screens.catalog.composables.CategoryTab
import ru.test.foodies.screens.catalog.composables.ProductCatalog
import ru.test.foodies.screens.catalog.composables.TopBar


val margin1 = 16.dp
val margin2 = 8.dp
val textSizeCard = 14.sp
val textSizeButton = 16.sp


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CatalogScreen(viewModel: ViewModel, navController: NavHostController) {
    val model: Model by viewModel.data.observeAsState(Model())
    val products = model.products
    val cart = products.filter { it.count > 0 }
    val cartEmpty = cart.isEmpty()

    Scaffold(topBar = {
        Column(Modifier.fillMaxWidth()) {
            TopBar(navController)

        }
    }, bottomBar = {
        if (!cartEmpty) {
            BottomBar()
        }
    }) {
        CategoryTab()
        ProductCatalog(list = products, navController = navController, it)
    }

}














