package com.paixao.dev.mbtest.data.service

import com.paixao.dev.mbtest.data.response.ExchangeResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("/exchanges")
    suspend fun getExchanges(): Response<ExchangeResponse>

    @GET("/exchanges")
    suspend fun getExchanges(@Query("filter_exchange_id") exchangeID: String): ResponseBody
}