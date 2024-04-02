package ru.test.foodies.screens.elements

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.androiddevtask.R
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.textSizeButton
import ru.test.foodies.ui.theme.Orange


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun BottomBar() {
    val viewModel: ViewModel = viewModel()
    val cart by viewModel.cart.observeAsState(listOf())
    var sum = 0
    for (product in cart) {
        if (product.price_current != null) {
            sum += product.price_current * product.count
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(68.dp)
    )
    {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Orange, RoundedCornerShape(8.dp))
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = stringResource(id = R.string.cart),
                    tint = Color.White,
                )
                Text(
                    text = "${sum / 100} ₽",
                    fontSize = textSizeButton,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}