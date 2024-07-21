@file:Suppress("KotlinConstantConditions")

package ru.test.foodies.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.androiddevtask.R
import ru.test.foodies.model.ProductsModel
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.margin1
import ru.test.foodies.screens.catalog.textSizeButton
import ru.test.foodies.screens.product.composables.BottomBarProductCard
import ru.test.foodies.screens.product.composables.ProductCardItem
import ru.test.foodies.screens.product.composables.ProductCardItemBottom
import ru.test.foodies.ui.theme.GrayText


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ProductScreen(productId: String?, viewModel: ViewModel, navController: NavHostController) {
    val productsModel: ProductsModel by viewModel.products.observeAsState(ProductsModel())
    val product = productsModel.products.find { it.id == productId?.toInt() }
    val scrollState = rememberScrollState()
    if (product != null) {
        Scaffold(Modifier.fillMaxSize(), bottomBar = {
            BottomBarProductCard(product = product)
        }, content = {

            ConstraintLayout(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(it)
                    .verticalScroll(scrollState)
            ) {
                val (backButton, content) = createRefs()

                FloatingActionButton(
                    onClick = { navController.popBackStack() },
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    shape = CircleShape,
                    modifier = Modifier
                        .constrainAs(backButton) {
                            start.linkTo(parent.start, margin1)
                            top.linkTo(parent.top, margin1)
                        }
                ) {
                    Icon(
                        contentDescription = stringResource(id = R.string.back),
                        painter = painterResource(id = R.drawable.ic_arrow_left)
                    )
                }

                Column(
                    Modifier
                        .constrainAs(content) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }) {
                    Image(
                        painter = painterResource(id = R.drawable.card_photo_example_375),
                        contentDescription = stringResource(id = R.string.card_photo),
                        Modifier.size(420.dp)
                    )
                    val tags = product.tag_ids
                    Row(Modifier.padding(horizontal = 16.dp)) {
                        if (product.price_old != null) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_discount),
                                contentDescription = stringResource(
                                    id = R.string.discount
                                ),
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                        if (tags.contains(2)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_vegan),
                                contentDescription = stringResource(
                                    id = R.string.vegan
                                ),
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                        if (tags.contains(4)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_spicy),
                                contentDescription = stringResource(
                                    id = R.string.spicy
                                ),
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }

                    }

                    Text(
                        lineHeight = 34.sp,
                        text = product.name,
                        color = Color.Black,
                        fontSize = 34.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = product.description.toString(),
                        color = GrayText,
                        fontSize = textSizeButton,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    Column {
                        if (product.measure != 0 && product.measure != 1) {
                            ProductCardItem(
                                string1 = "Вес",
                                string2 = "${product.measure.toString()} ${product.measure_unit.toString()}"
                            )
                        }
                        if (product.energy_per_100_grams != 0.0 && product.energy_per_100_grams != 1.0) {
                            ProductCardItem(
                                string1 = "Энерг. ценность",
                                string2 = product.energy_per_100_grams.toString() + " ккал"
                            )
                        }
                        if (product.proteins_per_100_grams != 0.0 && product.proteins_per_100_grams != 1.0) {
                            ProductCardItem(
                                string1 = "Белки",
                                string2 = product.proteins_per_100_grams.toString() + " г"
                            )
                        }
                        if (product.fats_per_100_grams != 0.0 && product.fats_per_100_grams != 1.0) {
                            ProductCardItem(
                                string1 = "Жиры",
                                string2 = product.fats_per_100_grams.toString() + " г"
                            )
                        }
                        if (product.carbohydrates_per_100_grams != 0.0 && product.carbohydrates_per_100_grams != 1.0) {
                            ProductCardItemBottom(
                                string1 = "Углеводы",
                                string2 = product.carbohydrates_per_100_grams.toString() + " г"
                            )
                        }

                    }
                }
            }

        })
    }


}