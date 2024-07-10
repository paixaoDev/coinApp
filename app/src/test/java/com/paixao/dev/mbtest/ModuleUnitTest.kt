package com.paixao.dev.mbtest

import com.paixao.dev.mbtest.di.repositoryModule
import com.paixao.dev.mbtest.di.roomModule
import com.paixao.dev.mbtest.di.useCaseModule
import com.paixao.dev.mbtest.di.viewModelModule
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class ModuleUnitTest {

    @Before
    fun setup() {
        startKoin {
            modules(listOf(repositoryModule, roomModule, useCaseModule, viewModelModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `repositoryModule should provide CoinRepository`() {
        val repository = get()
        assertNotNull(repository)
    }

    @Test
    fun `roomModule should provide CoinDataBase and CoinDataDao`() {
        val database = get()
        val dao = get()
        assertNotNull(database)
        assertNotNull(dao)
    }

    @Test
    fun `useCaseModule should provide FetchExchangeListUseCase and FetchExchangeDetailUseCase`() {
        val exchangeListUseCase = get()
        val exchangeDetailUseCase = get()
        assertNotNull(exchangeListUseCase)
        assertNotNull(exchangeDetailUseCase)
    }

    @Test
    fun `viewModelModule should provide ExchangeViewModel and ExchangeDetailViewModel`() {
        val exchangeViewModel = get()
        val exchangeDetailViewModel = get()
        assertNotNull(exchangeViewModel)
        assertNotNull(exchangeDetailViewModel)
    }
}