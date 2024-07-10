package com.paixao.dev.mbtest.presentation.viewmodel

import android.net.NetworkInfo.DetailedState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCase
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.domain.utils.Result
import com.paixao.dev.mbtest.presentation.model.toModel
import com.paixao.dev.mbtest.presentation.state.CoinUiState
import com.paixao.dev.mbtest.presentation.state.DetailScreenUiState
import com.paixao.dev.mbtest.presentation.state.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinViewModel(
    private val fetchExchangeListUseCase: FetchExchangeListUseCase,
    private val fetchExchangeDetailUseCase: FetchExchangeDetailUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CoinUiState>(CoinUiState.Loading())
    val state: StateFlow<CoinUiState> = _state.asStateFlow()

    fun getExchangeList() {
        fetchExchangeListUseCase()
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value =
                            HomeScreenUiState.ExchangeList(result.data.map { it.toModel() })
                    }

                    is Result.Error -> {
                        _state.value = CoinUiState.Error(result.error)
                    }

                    is Result.Failure -> {
                        _state.value = CoinUiState.Failure(result.throwable)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getExchange(exchangeID: String) {
        fetchExchangeDetailUseCase(exchangeID)
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = DetailScreenUiState.ExchangeDetail(result.data.toModel())
                    }

                    is Result.Error -> {
                        _state.value = CoinUiState.Error(result.error)
                    }

                    is Result.Failure -> {
                        _state.value = CoinUiState.Failure(result.throwable)
                    }
                }
            }.launchIn(viewModelScope)
    }
}
