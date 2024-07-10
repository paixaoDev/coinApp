package com.paixao.dev.mbtest

import com.paixao.dev.mbtest.data.database.CoinDataDao
import com.paixao.dev.mbtest.data.database.ExchangeDetailDataEntity
import com.paixao.dev.mbtest.data.database.ExchangeImageDataEntity
import com.paixao.dev.mbtest.data.repository.CoinRepositoryImpl
import com.paixao.dev.mbtest.data.response.ExchangeIconResponse
import com.paixao.dev.mbtest.data.service.CoinApi
import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.utils.Result
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import java.math.BigDecimal
import kotlin.test.Test

class RepositoryUnitTest {
    @MockK
    private lateinit var service: CoinApi

    @MockK
    private lateinit var database: CoinDataDao

    private lateinit var repository: CoinRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = CoinRepositoryImpl(service, database)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchExchange should return success result from database`() = runTest {
        // Given
        val databaseExchanges = listOf(
            getExchangeDetailDataEntityMock("1"),
            getExchangeDetailDataEntityMock("2")
        )
        coEvery { database.getDatabaseExchanges() } returns databaseExchanges

        // When
        val result = repository.fetchExchange()

        // Then
        assertEquals(
            Result.Success(
                listOf(
                    getExchangeEntityMock("1"),
                    getExchangeEntityMock("2")
                )
            ), result
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchExchangeIcons should return success result from database`() = runTest {
        // Given
        val databaseIcons =
            listOf(ExchangeImageDataEntity("id1", "icon1"), ExchangeImageDataEntity("id2", "icon2"))
        coEvery { database.getDatabaseExchangeIcons() } returns databaseIcons

        // When
        val result = repository.fetchExchangeIcons()

        // Then
        assertEquals(
            listOf(ExchangeIconEntity("id1", "icon1"), ExchangeIconEntity("id2", "icon2")),
            result
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getExchange should return exchange entity from database`() = runTest {
        // Given
        val databaseExchange = getExchangeDetailDataEntityMock("1")
        coEvery { database.searchExchange("1") } returns listOf(databaseExchange)

        // When
        val result = repository.getExchange("1")

        // Then
        assertEquals(getExchangeEntityMock("1"), result)
    }

    private fun getExchangeDetailDataEntityMock(id: String = "1"): ExchangeDetailDataEntity =
        ExchangeDetailDataEntity(
            id, "", "", "",
            BigDecimal.ONE.toString(),
            BigDecimal.ONE.toString(),
            BigDecimal.ONE.toString()
        )

    private fun getExchangeEntityMock(id: String = "1"): ExchangeEntity =
        ExchangeEntity(
            id, "", "", "",
            BigDecimal.ONE,
            BigDecimal.ONE,
            BigDecimal.ONE
        )

    private fun getExchangeImageDataEntityMock(id: String = "1"): ExchangeImageDataEntity =
        ExchangeImageDataEntity(
            id, "",
        )

    private fun getExchangeIconResponseMock(id: String = "1"): ExchangeIconResponse =
        ExchangeIconResponse(id, "icon1")
}