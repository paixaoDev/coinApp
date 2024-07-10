package com.paixao.dev.mbtest.presentation.state

import com.paixao.dev.mbtest.presentation.model.ExchangeItem

sealed class HomeScreenUiState : CoinUiState() {
    data class ExchangeList(val exchanges: List<ExchangeItem>) : HomeScreenUiState()
}