package com.paixao.dev.mbtest.di

import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCase
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.presentation.viewmodel.ExchangeDetailViewModel
import com.paixao.dev.mbtest.presentation.viewmodel.ExchangeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideExchangeViewModel(
    exchangeListUseCase: FetchExchangeListUseCase
): ExchangeViewModel {
    return ExchangeViewModel(exchangeListUseCase)
}

fun provideExchangeDetailViewModel(
    exchangeDetailUseCase: FetchExchangeDetailUseCase
): ExchangeDetailViewModel {
    return ExchangeDetailViewModel(exchangeDetailUseCase)
}

val viewModelModule = module {
    viewModel { provideExchangeViewModel(get()) }
    viewModel { provideExchangeDetailViewModel(get()) }
}
