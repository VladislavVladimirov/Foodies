package ru.test.foodies.screens.catalog.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.model.Model
import ru.test.foodies.model.ViewModel
import ru.test.foodies.ui.theme.Orange


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CategoryTab() {
    val viewModel: ViewModel = viewModel()
    val model: Model by viewModel.data.observeAsState(Model())
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = model.categories.map { it.name }
    Row(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title.toString(), color = Color.Black) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    modifier = Modifier.background(color = Orange, shape = RoundedCornerShape(8.dp))
                )
            }
        }
    }
}




