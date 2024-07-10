package com.paixao.dev.mbtest.domain.repository

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity
import com.paixao.dev.mbtest.domain.utils.Result

interface CoinRepository {
    suspend fun fetchExchange(): Result<List<ExchangeEntity>, String>
    suspend fun fetchExchangeIcons(): List<ExchangeIconEntity>?
    suspend fun getExchange(exchangeId: String): ExchangeEntity
}