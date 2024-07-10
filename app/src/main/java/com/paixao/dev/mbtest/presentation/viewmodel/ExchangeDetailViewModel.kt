package com.paixao.dev.mbtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCase
import com.paixao.dev.mbtest.presentation.model.toDetailModel
import com.paixao.dev.mbtest.presentation.state.CoinUiState
import com.paixao.dev.mbtest.presentation.state.DetailScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ExchangeDetailViewModel(
    private val fetchExchangeDetailUseCase: FetchExchangeDetailUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<DetailScreenUiState>(DetailScreenUiState.Loading())
    val state: StateFlow<DetailScreenUiState> = _state.asStateFlow()

    fun getExchange(exchangeID: String) {
        _state.value = DetailScreenUiState.Loading()
        fetchExchangeDetailUseCase(exchangeID)
            .onEach { result ->
                _state.value = DetailScreenUiState.ExchangeDetail(result.toDetailModel())
            }.launchIn(viewModelScope)
    }
}
