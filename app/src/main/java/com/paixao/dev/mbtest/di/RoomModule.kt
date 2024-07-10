package com.paixao.dev.mbtest.di

import android.app.Application
import androidx.room.Room
import com.paixao.dev.mbtest.data.database.CoinDataBase
import com.paixao.dev.mbtest.data.database.CoinDataDao
import org.koin.dsl.module

fun provideRoom(application: Application): CoinDataBase {
    return Room.databaseBuilder(
        application,
        CoinDataBase::class.java,
        "coin_db"
    )
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDao(database: CoinDataBase): CoinDataDao {
    return database.getDao()
}

val roomModule = module {
    single { provideRoom(get()) }
    single { provideDao(get()) }
}