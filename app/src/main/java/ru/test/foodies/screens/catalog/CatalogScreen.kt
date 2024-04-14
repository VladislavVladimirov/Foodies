package ru.test.foodies.screens.catalog


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.model.Model
import ru.test.foodies.model.ViewModel


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

    Scaffold(topBar = { TopBar(navController) }, bottomBar = {
        if (!cartEmpty) {
            BottomBar()
        }
    }) {

        ProductCatalog(list = products, navController = navController, it)

    }

}














