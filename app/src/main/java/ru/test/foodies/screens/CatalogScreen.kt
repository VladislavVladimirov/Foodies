package ru.test.foodies.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.model.Model
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.elements.BottomBar
import ru.test.foodies.screens.elements.ProductCatalog
import ru.test.foodies.screens.elements.TopBar


val margin1 = 16.dp
val margin2 = 8.dp
val textSizeCard = 14.sp
val textSizeButton = 16.sp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CatalogScreen(viewModel: ViewModel) {
    val model: Model by viewModel.data.observeAsState(Model())
    val products = model.products
    val cart by viewModel.cart.observeAsState(listOf())
    val cartEmpty = cart.isEmpty()
    Scaffold(Modifier.fillMaxSize(), topBar = { TopBar() }, bottomBar = {
        if (!cartEmpty) {
            BottomBar()
        }
    }, content = { ProductCatalog(list = products) })
}














