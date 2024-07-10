package com.paixao.dev.mbtest.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paixao.dev.mbtest.compose.component.ExchangeListItem
import com.paixao.dev.mbtest.compose.component.InformativeText
import com.paixao.dev.mbtest.presentation.model.ExchangeItem
import com.paixao.dev.mbtest.presentation.state.HomeScreenUiState
import com.paixao.dev.mbtest.presentation.viewmodel.ExchangeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    viewModel: ExchangeViewModel = koinViewModel(),
    onExchangeClick: (exchange: String) -> Unit = {}
) {
    val state by viewModel.state.collectAsState(initial = HomeScreenUiState.Loading())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val ui = state) {
            is HomeScreenUiState.ExchangeList -> HomeScreenComposable(ui.exchanges, onExchangeClick)
            is HomeScreenUiState.Error -> InformativeText(ui.error)
            is HomeScreenUiState.Failure -> InformativeText(ui.exception.localizedMessage?: "Erro")
            is HomeScreenUiState.Loading ->InformativeText("Carregado...")
        }
    }
}

@Composable
fun HomeScreenComposable(
    exchanges: List<ExchangeItem>,
    onExchangeClick: (exchange: String) -> Unit = {}
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

