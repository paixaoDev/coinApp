package com.paixao.dev.mbtest.data.repository

import com.paixao.dev.mbtest.data.response.toEntity
import com.paixao.dev.mbtest.data.service.CoinApi
import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.utils.Result

class CoinRepositoryImpl(
    val service: CoinApi
) : CoinRepository {
    override suspend fun fetchExchange(): Result<ExchangeEntity, String> {
        return try {
            val response = service.getExchanges()
            if (response.isSuccessful) {
                val model = response.body() ?: return Result.Failure()
                Result.Success(model.toEntity())
            } else {
                Result.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}