package com.paixao.dev.mbtest.data.repository

import com.paixao.dev.mbtest.data.response.toEntity
import com.paixao.dev.mbtest.data.service.CoinApi
import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.entities.ExchangeDetailEntity
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.utils.Result
import java.math.BigDecimal

class CoinRepositoryImpl(
    private val service: CoinApi
) : CoinRepository {
    override suspend fun fetchExchange(): Result<List<ExchangeEntity>, String> {
        return try {
            val response = service.getExchanges()

            if (response.isSuccessful) {
                val model = response.body() ?: return Result.Error("Empty list")
                val value = model.map { it.toEntity() }
                Result.Success(value)
            } else {
                Result.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun fetchExchangeIcons(): List<ExchangeIconEntity>? {
        val response = service.getExchangesIcons()
        if (response.isSuccessful) {
            val iconsModel = response.body() ?: return null
            return iconsModel.map { it.toEntity() }
        } else {
            return null
        }
    }

    override suspend fun fetchExchangeDetail(exchangeID: String): Result<ExchangeDetailEntity, String> {
        return try {
            val response = service.getExchanges(exchangeID)
            if (response.isSuccessful) {
                val model = response.body() ?: return Result.Error("Empty list")
                Result.Success(model.toEntity())
            } else {
                Result.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}