package com.paixao.dev.mbtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    //val fetchExchangeListUseCase: FetchExchangeListUseCase
) : ViewModel() {

    fun getExchangeList() {
//        fetchExchangeListUseCase()
//            .onEach {
//            }.launchIn(viewModelScope)
    }
}