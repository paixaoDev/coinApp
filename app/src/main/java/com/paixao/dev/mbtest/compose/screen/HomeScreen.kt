package com.paixao.dev.mbtest.compose.screen

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paixao.dev.mbtest.R
import com.paixao.dev.mbtest.compose.component.ExchangeItem
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel


@Composable
fun HomeScreen(
    viewModel: CoinViewModel = viewModel()
) {
    ExchangeItem(
        id = "NUB_00",
        name = "Nubank",
        image = painterResource(id = R.drawable.ic_launcher_background),
        value = "$ 100K+",
        time = "Em 24 Horaas",
        fav = true
    )
}