package com.paixao.dev.mbtest.domain.usecase

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal

interface FetchExchangeListUseCase {
    operator fun invoke(): Flow<Result<List<ExchangeEntity>, String>>
}

class FetchExchangeListUseCaseImpl(
    private val repository: CoinRepository
) : FetchExchangeListUseCase {
    override fun invoke(): Flow<Result<List<ExchangeEntity>, String>> {
        val exchange = flow {
            emit(repository.fetchExchange())
        }
        val icons = flow {
            emit(repository.fetchExchangeIcons())
        }

        val result = combine(exchange, icons) { exchangeResult, iconsResult ->
            when (exchangeResult) {
                is Result.Success -> {
                    val data = exchangeResult.data.toMutableList()

                    iconsResult?.let {
                        data.map { exchange ->
                            exchange.image = iconsResult.find { it.id == exchange.id }?.url
                        }
                    }

                    data.removeAll { it.volumeDay < BigDecimal.ONE }
                    Result.Success(data)
                }

                is Result.Error -> exchangeResult
                is Result.Failure -> exchangeResult
            }
        }

        return result
    }
}
