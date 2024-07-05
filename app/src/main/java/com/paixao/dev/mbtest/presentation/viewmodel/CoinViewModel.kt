package com.paixao.dev.mbtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
class CoinViewModel (
    private val fetchExchangeListUseCase: FetchExchangeListUseCase
) : ViewModel() {

    fun getExchangeList() {
        fetchExchangeListUseCase()
            .onEach {
            }.launchIn(viewModelScope)
    }
}