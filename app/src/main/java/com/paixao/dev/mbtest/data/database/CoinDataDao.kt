package com.paixao.dev.mbtest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeList(list: List<ExchangeDetailDataEntity>)

    @Query("DELETE FROM ExchangeDetailDataEntity")
    suspend fun deleteAllExchanges()

    @Query("SELECT * FROM ExchangeDetailDataEntity WHERE id GLOB '*' || :searchQuery || '*' OR id GLOB '*' ||  :searchQuery || '*' OR id  GLOB :searchQuery || '*' OR id  GLOB '*' || :searchQuery || '*' ")
    suspend fun searchExchange(searchQuery: String?): List<ExchangeDetailDataEntity>

    @Query("SELECT * FROM ExchangeDetailDataEntity")
    suspend fun getDatabaseExchanges(): List<ExchangeDetailDataEntity>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeIconList(list: List<ExchangeImageDataEntity>)

    @Query("DELETE FROM ExchangeImageDataEntity")
    suspend fun deleteAllExchangeIcons()

    @Query("SELECT * FROM ExchangeImageDataEntity")
    suspend fun getDatabaseExchangeIcons(): List<ExchangeImageDataEntity>?
}