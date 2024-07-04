package com.paixao.dev.mbtest.domain.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

const val BASE_URL = "http://rest.coinapi.io/v1"

interface CoinApi {

    @GET("$BASE_URL/exchangerate/")
    suspend fun getExchanges(): ResponseBody
}