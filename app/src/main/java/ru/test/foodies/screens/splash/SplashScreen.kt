package ru.test.foodies.screens.splash

import android.graphics.Color
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import ru.test.androiddevtask.R
import ru.test.foodies.ui.theme.Orange


@Composable
fun SplashScreen(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
        delay(1000L)
        navController.navigate("Catalog") {
            popUpTo("Splash") { inclusive = true }
        }
    }
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Orange
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Orange)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_white),
            contentDescription = "Logo",
        )
    }
}
