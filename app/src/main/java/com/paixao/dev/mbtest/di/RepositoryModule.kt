package com.paixao.dev.mbtest.di

import com.paixao.dev.mbtest.data.repository.CoinRepositoryImpl
import com.paixao.dev.mbtest.data.service.CoinApi
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import org.koin.dsl.module

fun provideCoinRepository(
    api: CoinApi
): CoinRepository {
    return CoinRepositoryImpl(api)
}

val repositoryModule = module {
    factory { provideCoinRepository(get()) }
}