package com.paixao.dev.mbtest.di

import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCaseImpl
import org.koin.dsl.module

fun provideExchangeUseCase(
    repository: CoinRepository
): FetchExchangeListUseCase {
    return FetchExchangeListUseCaseImpl(repository)
}

val useCaseModule = module {
    factory { provideExchangeUseCase(get()) }
}