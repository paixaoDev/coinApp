package com.paixao.dev.mbtest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paixao.dev.mbtest.compose.screen.DetailsScreen
import com.paixao.dev.mbtest.compose.screen.HomeScreen
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel

@Composable
fun CoinApp(
    viewModel: CoinViewModel
) {
    val navController = rememberNavController()
    CoinNavHost(viewModel, navController)
}

@Composable
fun CoinNavHost(
    viewModel: CoinViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            viewModel.getExchangeList()
            HomeScreen(
                viewModel,
                onExchangeClick = { exchange ->
                    navController.navigate("details/${exchange}"){
                        popUpTo("home") {
                            inclusive = false
                            saveState = true
                        }
                    }
                }
            )
        }
        composable("details/{exchange}") { entry ->
            entry.arguments?.getString("exchange")?.let { exchangeID ->
                viewModel.getExchange(exchangeID)
                DetailsScreen(viewModel, exchangeID)
            } ?: LaunchedEffect(key1 = null) {
                navController.navigate("home")
            }
        }
    }
}
