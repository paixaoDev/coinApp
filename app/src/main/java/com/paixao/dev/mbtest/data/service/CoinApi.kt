package com.paixao.dev.mbtest.data.service

import com.paixao.dev.mbtest.data.response.ExchangeResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("/v1/exchangerate/USD?invert=false")
    suspend fun getExchanges(): Response<ExchangeResponse>

    @GET("/v1/exchangerate/")
    suspend fun getExchanges(@Query("filter_exchange_id") exchangeID: String): ResponseBody
}