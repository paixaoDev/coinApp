package com.paixao.dev.mbtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExchangeDetailDataEntity::class , ExchangeImageDataEntity::class],
    version = 5
)
abstract class CoinDataBase: RoomDatabase(){
    abstract fun getDao(): CoinDataDao
}