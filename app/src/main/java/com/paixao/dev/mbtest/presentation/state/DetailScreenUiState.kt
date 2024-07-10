package com.paixao.dev.mbtest.presentation.state

import com.paixao.dev.mbtest.presentation.model.ExchangeDetails

sealed class DetailScreenUiState : CoinUiState() {
    data class ExchangeDetail(val exchangeDetails: ExchangeDetails) : DetailScreenUiState()
}