package com.paixao.dev.mbtest.presentation.state

import com.paixao.dev.mbtest.presentation.model.ExchangeDetails

sealed class DetailScreenUiState {
    data class Loading(val isLoading: Boolean = false) : DetailScreenUiState()
    data class Error(val error: String) : DetailScreenUiState()
    data class Failure(val exception: Throwable) : DetailScreenUiState()

    data class ExchangeDetail(val exchangeDetails: ExchangeDetails) : DetailScreenUiState()
}