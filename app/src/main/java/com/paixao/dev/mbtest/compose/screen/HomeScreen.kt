package com.paixao.dev.mbtest.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paixao.dev.mbtest.compose.component.ExchangeListItem
import com.paixao.dev.mbtest.presentation.model.ExchangeItem
import com.paixao.dev.mbtest.presentation.state.CoinUiState
import com.paixao.dev.mbtest.presentation.state.HomeScreenUiState
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel


@Composable
fun HomeScreen(
    viewModel: CoinViewModel = viewModel(),
    onExchangeClick: (exchange: String) -> Unit = {}
) {

    val uiState by viewModel.state.collectAsState(initial = CoinUiState.Loading())

    when (uiState) {
        is HomeScreenUiState.ExchangeList -> {
            HomeScreenComposable(
                (uiState as HomeScreenUiState.ExchangeList).exchanges,
                onExchangeClick
            )
        }

        else -> {}
    }
}

@Composable
fun HomeScreenComposable(
    exchanges: List<ExchangeItem>,
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
            items(
                exchanges
            ) {
                ExchangeListItem(
                    id = it.id,
                    name = it.name,
                    image = it.image,
                    value = it.value,
                    elevate = it.name == "Mercado Bitcoin",
                    fav = it.name == "Mercado Bitcoin"
                ) {
                    onExchangeClick.invoke(it.id)
                }
            }
        }
    }
}
