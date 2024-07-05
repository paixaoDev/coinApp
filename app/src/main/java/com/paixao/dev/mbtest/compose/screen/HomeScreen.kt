package com.paixao.dev.mbtest.compose.screen

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel


@Composable
fun HomeScreen(
    viewModel: CoinViewModel
) {
    Button(onClick = { viewModel.getExchangeList() }) {

    }
}