package com.paixao.dev.mbtest.data.response

import com.google.gson.annotations.SerializedName
import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import java.math.BigDecimal

data class ExchangeResponse(
    @SerializedName("exchange_id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("volume_1hrs_usd")
    val volumeHrs: BigDecimal,
    @SerializedName("volume_1day_usd")
    val volumeDay: BigDecimal,
    @SerializedName("volume_1mth_usd")
    val volumeMonth: BigDecimal
)

internal fun ExchangeResponse.toEntity(): ExchangeEntity {
    return ExchangeEntity(
        id = id ?: "",
        name = name ?: "",
        website = website ?: "",
        volumeDay = volumeDay,
        volumeHrs = volumeHrs,
        volumeMonth = volumeMonth
    )
}
