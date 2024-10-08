package com.paixao.dev.mbtest.data.service

import com.paixao.dev.mbtest.data.response.ExchangeIconResponse
import com.paixao.dev.mbtest.data.response.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {
    @GET("/v1/exchanges")
    suspend fun getExchanges(): Response<List<ExchangeResponse>>

    @GET("/v1/exchanges/icons/64")
    suspend fun getExchangesIcons(): Response<List<ExchangeIconResponse>>
}
