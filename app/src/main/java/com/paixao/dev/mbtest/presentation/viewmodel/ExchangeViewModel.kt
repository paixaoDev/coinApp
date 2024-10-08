package com.paixao.dev.mbtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.domain.utils.Result
import com.paixao.dev.mbtest.presentation.model.toModel
import com.paixao.dev.mbtest.presentation.state.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ExchangeViewModel(
    private val fetchExchangeListUseCase: FetchExchangeListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading())
    val state: StateFlow<HomeScreenUiState> = _state.asStateFlow()

    init {
        getExchangeList()
    }

    fun getExchangeList() {
        _state.value = HomeScreenUiState.Loading()
        fetchExchangeListUseCase()
            .onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value =
                            HomeScreenUiState.ExchangeList(result.data.map { it.toModel() })
                    }

                    is Result.Error -> {
                        _state.value = HomeScreenUiState.Error(result.error)
                    }

                    is Result.Failure -> {
                        _state.value = HomeScreenUiState.Failure(result.throwable)
                    }
                }
            }.launchIn(viewModelScope)
    }
}
