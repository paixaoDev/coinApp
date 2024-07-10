package com.paixao.dev.mbtest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paixao.dev.mbtest.domain.entities.ExchangeEntity

@Entity
data class ExchangeDetailDataEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val website: String?,
    var image: String?,
    val volumeHrs: String,
    val volumeDay: String,
    val volumeMonth: String
)

fun ExchangeDetailDataEntity.toEntity(): ExchangeEntity {
    return ExchangeEntity(
        id = id,
        name = name,
        website = website,
        image = image,
        volumeHrs = volumeHrs.toBigDecimal(),
        volumeDay = volumeDay.toBigDecimal(),
        volumeMonth = volumeMonth.toBigDecimal()
    )
}


fun ExchangeEntity.toDatabase(): ExchangeDetailDataEntity {
    return ExchangeDetailDataEntity(
        id = id,
        name = name,
        website = website,
        image = image,
        volumeHrs = volumeHrs.toString(),
        volumeDay = volumeDay.toString(),
        volumeMonth = volumeMonth.toString()
    )
}
