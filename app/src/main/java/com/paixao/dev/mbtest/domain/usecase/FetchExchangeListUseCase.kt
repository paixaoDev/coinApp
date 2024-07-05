package com.paixao.dev.mbtest.domain.usecase

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchExchangeListUseCase {
    operator fun invoke(): Flow<Result<ExchangeEntity, String>>
}

class FetchExchangeListUseCaseImpl(
    val repository: CoinRepository
) : FetchExchangeListUseCase {
    override fun invoke(): Flow<Result<ExchangeEntity, String>> {
        return flow {
            emit(repository.fetchExchange())
        }
    }
}



