package com.paixao.dev.mbtest.di

import com.paixao.dev.mbtest.data.database.CoinDataDao
import com.paixao.dev.mbtest.data.repository.CoinRepositoryImpl
import com.paixao.dev.mbtest.data.service.CoinApi
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import org.koin.dsl.module

fun provideCoinRepository(
    api: CoinApi,
    database: CoinDataDao
): CoinRepository {
    return CoinRepositoryImpl(api, database)
}

val repositoryModule = module {
    factory { provideCoinRepository(get(), get()) }
}