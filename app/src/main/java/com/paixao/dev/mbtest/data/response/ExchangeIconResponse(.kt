package com.paixao.dev.mbtest.data.response

import com.google.gson.annotations.SerializedName
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity

data class ExchangeIconResponse(
    @SerializedName("exchange_id")
    val id: String,
    @SerializedName("url")
    val url: String?
)


internal fun ExchangeIconResponse.toEntity(): ExchangeIconEntity {
    return ExchangeIconEntity(
        id = id,
        url = url ?: ""
    )
}