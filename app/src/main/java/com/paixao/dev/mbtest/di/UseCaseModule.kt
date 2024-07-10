package com.paixao.dev.mbtest.di

import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCase
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCaseImpl
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCase
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCaseImpl
import org.koin.dsl.module

fun provideExchangeListUseCase(
    repository: CoinRepository
): FetchExchangeListUseCase {
    return FetchExchangeListUseCaseImpl(repository)
}

fun provideExchangeDetailUseCase(
    repository: CoinRepository
): FetchExchangeDetailUseCase {
    return FetchExchangeDetailUseCaseImpl(repository)
}

val useCaseModule = module {
    factory { provideExchangeListUseCase(get()) }
    factory { provideExchangeDetailUseCase(get()) }
}