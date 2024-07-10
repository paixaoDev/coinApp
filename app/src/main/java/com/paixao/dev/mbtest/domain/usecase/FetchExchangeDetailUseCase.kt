package com.paixao.dev.mbtest.domain.usecase

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchExchangeDetailUseCase {
    operator fun invoke(exchangeID: String): Flow<ExchangeEntity>
}

class FetchExchangeDetailUseCaseImpl(
    private val repository: CoinRepository
) : FetchExchangeDetailUseCase {
    override fun invoke(exchangeID: String): Flow<ExchangeEntity> {
        return flow {
            emit(repository.getExchange(exchangeID))
        }
    }
}
