package com.paixao.dev.mbtest.presentation.state

sealed class CoinUiState {
    data class Loading(val isLoading: Boolean = false) : CoinUiState()
    data class Error(val error: String) : CoinUiState()
    data class Failure(val exception: Throwable) : CoinUiState()
}
