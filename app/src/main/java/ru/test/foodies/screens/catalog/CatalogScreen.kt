package ru.test.foodies.screens.catalog


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.model.CategoriesModel
import ru.test.foodies.model.ProductsModel
import ru.test.foodies.model.TagsModel
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.composables.BottomBar
import ru.test.foodies.screens.catalog.composables.BottomSheet
import ru.test.foodies.screens.catalog.composables.TabBar
import ru.test.foodies.screens.catalog.composables.TopBar


val margin1 = 16.dp
val margin2 = 8.dp
val textSizeCard = 14.sp
val textSizeButton = 16.sp


@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(viewModel: ViewModel, navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val productsModel: ProductsModel by viewModel.products.observeAsState(ProductsModel())
    val categoriesModel: CategoriesModel by viewModel.categories.observeAsState(CategoriesModel())
    val tagsModel: TagsModel by viewModel.tags.observeAsState(TagsModel())
    val products = productsModel.products
    val categories = categoriesModel.categories
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
    }) { it ->

        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            TabBar(categories, filteredProducts, scope = scope, navController)
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

















