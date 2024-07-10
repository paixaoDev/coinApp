package com.paixao.dev.mbtest

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeDetailUseCaseImpl
import com.paixao.dev.mbtest.domain.usecase.FetchExchangeListUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.paixao.dev.mbtest.domain.utils.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.DefaultAsserter.fail

class UseCaseUnitTest {

    @MockK
    private lateinit var repository: CoinRepository
    private lateinit var detailsUseCase: FetchExchangeDetailUseCaseImpl
    private lateinit var exchangeUseCase: FetchExchangeListUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        detailsUseCase = FetchExchangeDetailUseCaseImpl(repository)
        exchangeUseCase = FetchExchangeListUseCaseImpl(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `invoke should call repository getExchange and emit result`() = runTest {
        // Given
        val exchangeID = "exchangeID"
        val exchangeEntity = ExchangeEntity(
            "name",
            "description",
            "",
            "",
            BigDecimal.ONE,
            BigDecimal.ONE,
            BigDecimal.ONE
        )
        coEvery { repository.getExchange(exchangeID) } returns exchangeEntity

        // When
        val flow = detailsUseCase.invoke(exchangeID)

        // Then
        flow.collect { result ->
            assertEquals(exchangeEntity, result)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `invoke should return combined result of exchange and icons`() = runTest {
        // Given
        val exchangeResult =
            Result.Success(
                listOf(
                    ExchangeEntity(
                        "id1",
                        "description1",
                        "",
                        "",
                        BigDecimal.ONE,
                        BigDecimal.ONE,
                        BigDecimal.ONE
                    ), ExchangeEntity(
                        "id2",
                        "description2",
                        "",
                        "",
                        BigDecimal.ONE,
                        BigDecimal.ONE,
                        BigDecimal.ONE
                    )
                )
            )
        
        val iconsResult = listOf(
            ExchangeIconEntity("id1", "icon1"),
            ExchangeIconEntity("id2", "icon2")
        )

        coEvery { repository.fetchExchange() } returns exchangeResult
        coEvery { repository.fetchExchangeIcons() } returns iconsResult

        // When
        val flow =  exchangeUseCase.invoke()

        flow.collect { result ->
            when (result) {
                is Result.Success -> {
                    assertEquals(2, result.data.size)
                    assertEquals("icon1", result.data[0].image)
                    assertEquals("icon2", result.data[1].image)
                }

                else -> fail("Expected Success result, but got $result")
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `invoke should return error result if exchange fetch fails`() = runTest {
        // Given
        val exchangeResult = Result.Error("Error fetching exchange")
        val iconsResult = null

        coEvery { repository.fetchExchange() } returns exchangeResult
        coEvery { repository.fetchExchangeIcons() } returns iconsResult

        // When
        val result = exchangeUseCase.invoke()

        result.collect { result ->
            when (result) {
                is Result.Error -> assertEquals("Error fetching exchange", result.error)
                else -> fail("Expected Error result, but got $result")
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `invoke should return exchanges result if icons fetch fails`() = runTest {
        // Given
        val exchangeResult =
            Result.Success(
                listOf(
                    ExchangeEntity(
                        "id1",
                        "description1",
                        "",
                        "",
                        BigDecimal.ONE,
                        BigDecimal.ONE,
                        BigDecimal.ONE
                    ), ExchangeEntity(
                        "id2",
                        "description2",
                        "",
                        "",
                        BigDecimal.ONE,
                        BigDecimal.ONE,
                        BigDecimal.ONE
                    )
                )
            )
        val iconsResult = null

        coEvery { repository.fetchExchange() } returns exchangeResult
        coEvery { repository.fetchExchangeIcons() } returns iconsResult

        // When
        val result = exchangeUseCase.invoke()

        // Then
        repository.fetchExchange()
        repository.fetchExchangeIcons()

        result.collect { result ->
            when (result) {
                is Result.Success -> {
                    assertEquals(2, result.data.size)
                    assertEquals("", result.data[0].image)
                    assertEquals("", result.data[1].image)
                }

                else -> fail("Expected Success result, but got $result")
            }
        }
    }
}