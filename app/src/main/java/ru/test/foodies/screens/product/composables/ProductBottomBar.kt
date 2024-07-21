package ru.test.foodies.screens.product.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.androiddevtask.R
import ru.test.foodies.dto.Product
import ru.test.foodies.model.ProductsModel
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.textSizeButton
import ru.test.foodies.ui.theme.Orange

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun BottomBarProductCard(product: Product) {
    val viewModel: ViewModel = viewModel()
    val price = product.price_current?.div(100)
    val productsModel: ProductsModel by viewModel.products.observeAsState(ProductsModel())
    val products = productsModel.products
    val count by remember { mutableIntStateOf(product.count) }
    var countVisible by remember { mutableStateOf(true) }
    if (count > 0) {
        countVisible = true
    }
    if (products.find { it.id == product.id }?.count == 0) {
        countVisible = false
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(68.dp)
    )
    {
        IconButton(
            onClick = {
                viewModel.addToCart(product)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Orange, RoundedCornerShape(8.dp))
        ) {
            if (countVisible) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = stringResource(id = R.string.cart),
                        tint = Color.White,
                    )
                    Text(
                        text = "В корзине $count шт.",
                        fontSize = textSizeButton,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            } else {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = stringResource(id = R.string.cart),
                        tint = Color.White,
                    )
                    Text(
                        text = "В корзину за $price ₽",
                        fontSize = textSizeButton,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = stringResource(id = R.string.cart),
                    tint = Color.White,
                )
                Text(
                    text = "В корзину за $price ₽",
                    fontSize = textSizeButton,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}