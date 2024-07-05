package com.paixao.dev.mbtest.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paixao.dev.mbtest.R
import com.paixao.dev.mbtest.compose.component.ExchangeItem
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel


@Composable
fun HomeScreen(
    viewModel: CoinViewModel = viewModel(),
    onExchangeClick: (exchange: String) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(10) { index ->
                ExchangeItem(
                    id = "NUB_00",
                    name = "Nubank",
                    image = painterResource(id = R.drawable.ic_launcher_background),
                    value = "$ 100K+",
                    time = "Em 24 Horaas",
                    fav = index == 3,
                    elevate = index == 3 || index == 5
                ){
                    onExchangeClick.invoke("NUB_00")
                }
            }
        }
    }
}