package com.paixao.dev.mbtest.domain.usecase

import com.paixao.dev.mbtest.domain.entities.ExchangeDetailEntity
import com.paixao.dev.mbtest.domain.repository.CoinRepository
import com.paixao.dev.mbtest.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchExchangeDetailUseCase {
    operator fun invoke(exchangeID: String): Flow<Result<ExchangeDetailEntity, String>>
}

class FetchExchangeDetailUseCaseImpl(
    private val repository: CoinRepository
) : FetchExchangeDetailUseCase {
    override fun invoke(exchangeID: String): Flow<Result<ExchangeDetailEntity, String>> {
        return flow {
            emit(repository.fetchExchangeDetail(exchangeID))
        }
    }
}
