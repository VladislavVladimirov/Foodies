package ru.test.foodies.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.androiddevtask.R
import ru.test.foodies.model.Model
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.BottomBar
import ru.test.foodies.screens.catalog.ProductCatalog
import ru.test.foodies.screens.catalog.textSizeButton
import ru.test.foodies.ui.theme.GrayText
import ru.test.foodies.ui.theme.Orange

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SearchScreen(viewModel: ViewModel, navController: NavHostController) {
    val model: Model by viewModel.data.observeAsState(Model())
    val products = model.products
    val cart = products.filter { it.count > 0 }
    val cartEmpty = cart.isEmpty()
    var input by remember { mutableStateOf("") }
    val list = products.filter { product -> product.name.contains(input, ignoreCase = true) }
    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = stringResource(
                            id = R.string.back
                        ),
                        tint = Orange
                    )
                }
                TextField(
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    value = input, onValueChange = {
                        input = it
                    },
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = "Найти блюдо",
                            color = GrayText,
                            fontSize = textSizeButton
                        )
                    }
                )
                if (input != "") {
                    IconButton(
                        onClick = { input = "" },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = stringResource(
                                id = R.string.back
                            ),
                        )
                    }
                }
            }
        }
    }, bottomBar = {
        if (!cartEmpty) {
            BottomBar()
        }}) {

        if (input != "") {
            if (list.isNotEmpty()) {
                ProductCatalog(list = list, navController = navController, paddingValues = it)
            } else {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        text = "Ничего не нашлось :(",
                        color = GrayText,
                        fontSize = textSizeButton,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }

        } else {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = "Введите название блюда,\nкоторое ищете",
                    color = GrayText,
                    fontSize = textSizeButton,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }

    }

}