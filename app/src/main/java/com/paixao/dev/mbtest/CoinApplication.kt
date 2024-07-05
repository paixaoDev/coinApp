package com.paixao.dev.mbtest

import android.app.Application
import com.paixao.dev.mbtest.di.networkModule
import com.paixao.dev.mbtest.di.repositoryModule
import com.paixao.dev.mbtest.di.useCaseModule
import com.paixao.dev.mbtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CoinApplication)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}