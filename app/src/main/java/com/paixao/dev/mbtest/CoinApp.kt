package com.paixao.dev.mbtest

import androidx.compose.runtime.Composable
import com.paixao.dev.mbtest.compose.screen.HomeScreen
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel

@Composable
fun CoinApp(viewModel: CoinViewModel) {
    HomeScreen(viewModel)
}