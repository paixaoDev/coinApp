package com.paixao.dev.mbtest.di


import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCase
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideCoinViewModel(
    exchangeListUseCase: FetchExchangeListUseCase,
    exchangeDetailUseCase: FetchExchangeDetailUseCase
): CoinViewModel {
    return CoinViewModel(exchangeListUseCase, exchangeDetailUseCase)
}

val viewModelModule = module {
    viewModel { provideCoinViewModel(get(), get()) }
}