package com.paixao.dev.mbtest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paixao.dev.mbtest.compose.screen.DetailsScreen
import com.paixao.dev.mbtest.compose.screen.HomeScreen

@Composable
fun CoinApp() {
    val navController = rememberNavController()
    CoinNavHost(navController)
}

@Composable
fun CoinNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onExchangeClick = { exchange ->
                    navController.navigate("details/${exchange}")
                }
            )
        }
        composable("details/{exchange}") { entry ->
            entry.arguments?.getString("exchange")?.let { exchange ->
                DetailsScreen(exchangeID = exchange)
            } ?: LaunchedEffect(key1 = null) {
                navController.navigate("home")
            }
        }
    }
}
