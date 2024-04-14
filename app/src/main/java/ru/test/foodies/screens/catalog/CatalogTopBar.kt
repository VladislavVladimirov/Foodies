package ru.test.foodies.screens.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import ru.test.androiddevtask.R
import ru.test.foodies.ui.theme.Orange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(title = {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (logo, search, settings) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(
                    id = R.string.logo_description,
                ),
                tint = Orange,
                modifier = Modifier
                    .constrainAs(logo) {
                        top.linkTo(parent.top, margin1)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        start.linkTo(settings.end, margin2)
                        end.linkTo(search.start, margin2)
                    }
                    .size(width = 271.dp, height = 44.dp)
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(settings) {
                start.linkTo(parent.start, margin2)
                bottom.linkTo(parent.bottom)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(
                        id = R.string.settings,
                    ),
                    tint = Color.Black,
                )
            }
            IconButton(onClick = { navController.navigate("Search screen")}, modifier = Modifier.constrainAs(search) {
                end.linkTo(parent.end, margin2)
                bottom.linkTo(parent.bottom)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(
                        id = R.string.search,
                    ),
                    tint = Color.Black,
                )
            }
        }

    }, modifier = Modifier.fillMaxWidth())
}