package ru.test.foodies.screens.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.androiddevtask.R
import ru.test.foodies.dto.Product
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.margin1
import ru.test.foodies.screens.margin2
import ru.test.foodies.screens.sample.SampleProductProvider
import ru.test.foodies.screens.textSizeButton
import ru.test.foodies.screens.textSizeCard
import ru.test.foodies.ui.theme.Gray
import ru.test.foodies.ui.theme.GrayText

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ProductCard(@PreviewParameter(SampleProductProvider::class) product: Product) {
    val viewModel: ViewModel = viewModel()
    var priceVisible by remember { mutableStateOf(true) }
    val cart by viewModel.cart.observeAsState(listOf())

    if (cart.find { it.id == product.id } == null) {
        priceVisible = true
    }
    Card(
        Modifier
            .padding(4.dp),
        RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray,
        )
    ) {
        ConstraintLayout {
            val (photo, tag, name, weight, addToCart) = createRefs()
            val tags = product.tag_ids
            Column(modifier = Modifier
                .constrainAs(tag) {
                    top.linkTo(parent.top, margin2)
                    start.linkTo(parent.start, margin2)
                }) {
                if (tags.contains(2)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_vegan),
                        contentDescription = stringResource(
                            id = R.string.vegan
                        ),
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                if (tags.contains(4)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_spicy),
                        contentDescription = stringResource(
                            id = R.string.spicy
                        ),
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                if (product.price_old != null) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_discount),
                        contentDescription = stringResource(
                            id = R.string.discount
                        ),
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }


            }

            Image(painter = painterResource(id = R.drawable.card_photo_example),
                contentDescription = stringResource(
                    id = R.string.card_photo
                ),
                modifier = Modifier
                    .constrainAs(photo) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .size(170.dp))
            product.name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(photo.bottom)
                            start.linkTo(parent.start, margin1)

                        }
                        .width(146.dp),
                    fontSize = textSizeCard,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = "${product.measure.toString()} ${product.measure_unit.toString()}",
                modifier = Modifier
                    .constrainAs(weight) {
                        top.linkTo(name.bottom)
                        start.linkTo(parent.start, margin1)
                    },
                fontSize = textSizeCard,
                color = GrayText,
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(addToCart) {
                    top.linkTo(weight.bottom, margin2)
                    bottom.linkTo(parent.bottom, margin1)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                if (priceVisible) {
                    TextButton(
                        onClick = {
                            viewModel.addToCart(product)
                            priceVisible = false
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    ) {
                        ConstraintLayout {
                            val (current, old) = createRefs()
                            Text(
                                text = "${(product.price_current?.div(100)).toString()} ₽",
                                color = Color.Black, modifier = Modifier.constrainAs(current) {
                                    start.linkTo(parent.start)
                                    end.linkTo(old.start)
                                }, fontWeight = FontWeight.Bold
                            )
                            if (product.price_old != null) {
                                Text(
                                    text = "${(product.price_old.div(100))} ₽",
                                    color = GrayText,
                                    fontSize = textSizeCard,
                                    textDecoration = TextDecoration.LineThrough,
                                    maxLines = 1, modifier = Modifier.constrainAs(old) {
                                        start.linkTo(current.end, margin2)
                                        end.linkTo(parent.end)
                                    }
                                )
                            }
                        }
                    }
                } else {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                        val (minus, count, plus) = createRefs()
                        Text(
                            text = cart.find { it.id == product.id }?.count.toString(),
                            fontSize = textSizeButton,
                            color = Color.Black,
                            modifier = Modifier.constrainAs(count) {
                                start.linkTo(minus.end, margin1)
                                end.linkTo(plus.start, margin1)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                            fontWeight = FontWeight.Bold
                        )


                        IconButton(onClick = {
                            viewModel.updateProductCount(product.id, change = -1)
                        },
                            modifier = Modifier
                                .constrainAs(minus) {
                                    start.linkTo(parent.start)
                                    end.linkTo(count.start)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_minus),
                                contentDescription = stringResource(
                                    id = R.string.minus,
                                ),
                            )
                        }
                        IconButton(
                            onClick = {
                                viewModel.updateProductCount(product.id, change = 1)
                            },
                            modifier = Modifier
                                .constrainAs(plus) {
                                    end.linkTo(parent.end)
                                    start.linkTo(count.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_plus),
                                contentDescription = stringResource(
                                    id = R.string.plus
                                )
                            )
                        }

                    }
                }

            }

        }
    }
}



