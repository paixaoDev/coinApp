package com.paixao.dev.mbtest.data.response

import com.google.gson.annotations.SerializedName
import com.paixao.dev.mbtest.domain.entities.ExchangeDetailEntity
import com.paixao.dev.mbtest.extensions.toCurrency
import java.math.BigDecimal

data class ExchangeDetailResponse(
    @SerializedName("exchange_id")
    val id: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("data_quote_start")
    val dataQuoteStart: String,
    @SerializedName("data_quote_end")
    val dataQuoteEnd: String,
    @SerializedName("data_orderbook_start")
    val dataOrderBookStart: String,
    @SerializedName("data_orderbook_end")
    val dataOrderBookEnd: String,
    @SerializedName("data_trade_start")
    val dataTradeStart: String,
    @SerializedName("data_trade_end")
    val dataTradeEnd: String,
    @SerializedName("data_symbols_count")
    val dataSymbolsCount: Int,
    @SerializedName("volume_1hrs_usd")
    val volumeHrs: BigDecimal,
    @SerializedName("volume_1day_usd")
    val volumeDay: BigDecimal,
    @SerializedName("volume_1mth_usd")
    val volumeMonth: BigDecimal
)


internal fun ExchangeDetailResponse.toEntity(): ExchangeDetailEntity {
    return ExchangeDetailEntity(
        id = id ?: "",
        name = name ?: "",
        website = website ?: "",
        volumeDay = volumeDay.toCurrency(),
        volumeHrs = volumeHrs.toCurrency(),
        volumeMonth = volumeMonth.toCurrency()
    )
}