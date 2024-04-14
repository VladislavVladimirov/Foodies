package ru.test.foodies.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.foodies.model.ViewModel
import ru.test.foodies.screens.catalog.CatalogScreen
import ru.test.foodies.screens.product.ProductScreen
import ru.test.foodies.screens.search.SearchScreen
import ru.test.foodies.ui.theme.AndroidDevTaskTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidDevTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Catalog") {
                        composable("Catalog") {
                            val viewModel = hiltViewModel<ViewModel>()
                            CatalogScreen(viewModel, navController)
                        }
                        composable("productScreen/{productId}"){backStackEntry ->
                            val viewModel = hiltViewModel<ViewModel>()
                            val productId = backStackEntry.arguments?.getString("productId")
                            ProductScreen(productId,  viewModel, navController)
                        }
                        composable("Search screen"){
                            val viewModel = hiltViewModel<ViewModel>()
                            SearchScreen(viewModel = viewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

