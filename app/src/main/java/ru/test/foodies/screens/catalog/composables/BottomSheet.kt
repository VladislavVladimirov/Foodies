package ru.test.foodies.screens.catalog.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.test.foodies.dto.Product
import ru.test.foodies.screens.catalog.margin1
import ru.test.foodies.screens.catalog.textSizeButton
import ru.test.foodies.ui.theme.Orange
import ru.test.foodies.util.DataFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    isOpened: MutableState<Boolean>,
    sheetState: SheetState,
    isSpicy: MutableState<Boolean>,
    isVegan: MutableState<Boolean>,
    isDiscount: MutableState<Boolean>,
    filteredProducts: SnapshotStateList<Product>,
    products: List<Product>,
    scope: CoroutineScope,
) {
    var filteredProductsState = remember { filteredProducts }

    if (isOpened.value) {
        ModalBottomSheet(sheetState = sheetState,
            dragHandle = {},
            onDismissRequest = { isOpened.value = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = margin1)
            ) {
                Text(
                    text = "Подобрать блюда",
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = margin1, top = 32.dp)
                )
                Box(
                    Modifier
                        .padding(bottom = margin1, top = margin1)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Без мяса",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = margin1)
                            .align(alignment = Alignment.CenterStart)
                    )
                    Checkbox(
                        checked = isVegan.value,
                        onCheckedChange = { isVegan.value = !isVegan.value },
                        Modifier.align(alignment = Alignment.CenterEnd)
                    )
                }
                Box(
                    Modifier
                        .padding(bottom = margin1)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Острое",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = margin1)
                            .align(alignment = Alignment.CenterStart)
                    )
                    Checkbox(
                        checked = isSpicy.value,
                        onCheckedChange = {
                            isSpicy.value = !isSpicy.value

                        },
                        Modifier.align(alignment = Alignment.CenterEnd)
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = margin1)
                ) {
                    Text(
                        text = "Со скидкой",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = margin1)
                            .align(alignment = Alignment.CenterStart)
                    )
                    Checkbox(
                        checked = isDiscount.value,
                        onCheckedChange = { isDiscount.value = !isDiscount.value },
                        Modifier.align(alignment = Alignment.CenterEnd)
                    )
                }
                IconButton(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                isOpened.value = false
                            }
                            filteredProductsState.clear()
                            filteredProductsState.addAll(DataFormatter.filterProducts(products, isVegan, isSpicy, isDiscount))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 32.dp)
                        .background(Orange, RoundedCornerShape(8.dp))
                ) {
                    Row {

                        Text(
                            text = "Готово",
                            fontSize = textSizeButton,
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

        }
    }

}
