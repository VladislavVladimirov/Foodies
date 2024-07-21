package ru.test.foodies.screens.catalog.composables

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.test.androiddevtask.R
import ru.test.foodies.dto.Category
import ru.test.foodies.dto.Product
import ru.test.foodies.screens.catalog.textSizeButton
import ru.test.foodies.ui.theme.GrayText
import ru.test.foodies.ui.theme.Orange


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabBar(
    categories: List<Category>,
    filteredProducts: SnapshotStateList<Product>,
    scope: CoroutineScope,
    navController: NavHostController,
) {
    val pagerState = rememberPagerState()
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }
    if (categories.isNotEmpty()) {
        ScrollableTabRow(
            modifier = Modifier.height(50.dp),
            edgePadding = 0.dp,
            contentColor = Color.White,
            selectedTabIndex = pagerState.currentPage,
            indicator = indicator
        ) {
            categories.forEachIndexed { index, title ->
                val isSelected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier.zIndex(2f).padding(bottom = 4.dp),
                    text = {
                        Text(text = title.name.toString(), color = if (isSelected) Color.White else Color.Black)
                           },
                    selected = isSelected,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                index,
                                pageOffset = 0f
                            )
                        }
                    }
                )
            }
        }
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = categories.size,
        state = pagerState
    ) { page ->
        Box(Modifier.fillMaxSize()) {
            if (filteredProducts.isNotEmpty()) {
                ProductCatalog(
                    list = filteredProducts.filter { it.category_id == categories[page].id },
                    navController = navController,
                )
            } else {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.filter_hint),
                        color = GrayText,
                        fontSize = textSizeButton,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CustomIndicator(tabPosition: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPosition[it].left
    }
    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPosition[it].right
    }
    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(2.dp)
            .fillMaxSize()
            .background(color = Orange, RoundedCornerShape(8.dp))
            .zIndex(1f)
    )


}