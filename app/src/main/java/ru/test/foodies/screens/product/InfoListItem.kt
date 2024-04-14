package ru.test.foodies.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.test.foodies.screens.catalog.textSizeButton
import ru.test.foodies.ui.theme.Gray
import ru.test.foodies.ui.theme.GrayText


@Composable
fun ProductCardItem(string1: String, string2: String) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val (text1, text2, topLine) = createRefs()
        Text(text = string1, color = GrayText, modifier = Modifier
            .constrainAs(text1) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .padding(horizontal = 16.dp, vertical = 13.dp), fontSize = textSizeButton)

        Text(text = string2, color = Color.Black, modifier = Modifier
            .constrainAs(text2) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .padding(horizontal = 16.dp, vertical = 13.dp), fontSize = textSizeButton)
        HorizontalDivider(Modifier.constrainAs(topLine) {
            top.linkTo(parent.top)
        }, color = Gray, thickness = 1.dp)
    }
}

@Composable
fun ProductCardItemBottom(string1: String, string2: String) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val (text1, text2, bottomLine, topLine) = createRefs()
        Text(text = string1, color = GrayText, modifier = Modifier
            .constrainAs(text1) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .padding(horizontal = 16.dp, vertical = 13.dp), fontSize = textSizeButton)

        Text(text = string2, color = Color.Black, modifier = Modifier
            .constrainAs(text2) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .padding(horizontal = 16.dp, vertical = 13.dp), fontSize = textSizeButton)
        HorizontalDivider(Modifier.constrainAs(topLine) {
            top.linkTo(parent.top)
        }, color = Gray, thickness = 1.dp)
        HorizontalDivider(Modifier.constrainAs(bottomLine) {
            bottom.linkTo(parent.bottom)
        }, color = Gray, thickness = 1.dp)
    }
}
