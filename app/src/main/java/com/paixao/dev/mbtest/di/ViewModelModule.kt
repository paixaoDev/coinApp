package com.paixao.dev.mbtest.di


import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideCoinViewModel(
    exchangeUseCase: FetchExchangeListUseCase
): CoinViewModel {
    return CoinViewModel(exchangeUseCase)
}

val viewModelModule = module {
    viewModel { provideCoinViewModel(get()) }
}