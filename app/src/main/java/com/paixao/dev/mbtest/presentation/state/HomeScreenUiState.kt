package com.paixao.dev.mbtest.presentation.state

import com.paixao.dev.mbtest.presentation.model.ExchangeItem

sealed class HomeScreenUiState {
    data class Loading(val isLoading: Boolean = false) : HomeScreenUiState()
    data class Error(val error: String) : HomeScreenUiState()
    data class Failure(val exception: Throwable) : HomeScreenUiState()

    data class ExchangeList(val exchanges: List<ExchangeItem>) : HomeScreenUiState()
}