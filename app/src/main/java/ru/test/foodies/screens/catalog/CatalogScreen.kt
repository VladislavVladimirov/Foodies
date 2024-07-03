package ru.test.foodies.screens.catalog


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.androiddevtask.R
import ru.test.foodies.model.Model
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.composables.BottomBar
import ru.test.foodies.screens.catalog.composables.BottomSheet
import ru.test.foodies.screens.catalog.composables.ProductCatalog
import ru.test.foodies.screens.catalog.composables.TopBar
import ru.test.foodies.ui.theme.GrayText


val margin1 = 16.dp
val margin2 = 8.dp
val textSizeCard = 14.sp
val textSizeButton = 16.sp


@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(viewModel: ViewModel, navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val model: Model by viewModel.data.observeAsState(Model())
    val products = model.products
    val cart = products.filter { it.count > 0 }
    val cartEmpty = cart.isEmpty()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val isOpened = remember {
        mutableStateOf(false)
    }
    val isSpicy = remember {
        mutableStateOf(false)
    }
    val isDiscount = remember {
        mutableStateOf(false)
    }
    val isVegan = remember {
        mutableStateOf(false)
    }
    val filteredProducts = remember {
        products.toMutableStateList()
    }
    filteredProducts.addAll(products)

    systemUiController.setSystemBarsColor(
        color = Color.White
    )
    Scaffold(topBar = {
        Column(Modifier.fillMaxWidth()) {
            TopBar(navController, isOpened)

        }
    }, bottomBar = {
        if (!cartEmpty) {
            BottomBar()
        }
    }) {
        // CategoryTab()
        if (filteredProducts.isNotEmpty()) {
            ProductCatalog(
                list = filteredProducts,
                navController = navController,
                it
            )
        } else {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.filter_hint),
                    color = GrayText,
                    fontSize = textSizeButton,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }

        BottomSheet(
            isOpened = isOpened,
            sheetState = sheetState,
            isSpicy,
            isVegan,
            isDiscount,
            filteredProducts,
            products,
            scope
        )
    }

}















