package com.paixao.dev.mbtest.data.repository

import com.paixao.dev.mbtest.data.database.CoinDataDao
import com.paixao.dev.mbtest.data.database.toDatabase
import com.paixao.dev.mbtest.data.database.toEntity
import com.paixao.dev.mbtest.data.response.toEntity
import com.paixao.dev.mbtest.data.service.CoinApi
import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.utils.Result

class CoinRepositoryImpl(
    private val service: CoinApi,
    private val database: CoinDataDao
) : CoinRepository {
    override suspend fun fetchExchange(): Result<List<ExchangeEntity>, String> {
        return try {
            val databaseExchange = database.getDatabaseExchanges()

            if (!databaseExchange.isNullOrEmpty()) {
                Result.Success(databaseExchange.map { it.toEntity() })
            } else {
                val response = service.getExchanges()

                if (response.isSuccessful) {
                    val model = response.body() ?: return Result.Error("Empty list")
                    val entity = model.map { it.toEntity() }
                    database.insertExchangeList(entity.map { it.toDatabase() })
                    Result.Success(entity)
                } else {
                    Result.Error(response.errorBody().toString())
                }
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun fetchExchangeIcons(): List<ExchangeIconEntity>? {
        val icons = database.getDatabaseExchangeIcons()

        if (!icons.isNullOrEmpty()) {
            return (icons.map { it.toEntity() })
        } else {
            val response = service.getExchangesIcons()
            if (response.isSuccessful) {
                val iconsModel = response.body() ?: return null
                val entity = iconsModel.map { it.toEntity() }

                database.insertExchangeIconList(entity.map { it.toDatabase() })
                return entity
            } else {
                return null
            }
        }
    }

    override suspend fun getExchange(exchangeId: String): ExchangeEntity {
        return database.searchExchange(exchangeId).map { it.toEntity() }[0]
    }
}