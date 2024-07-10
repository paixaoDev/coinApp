package com.paixao.dev.mbtest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paixao.dev.mbtest.domain.entities.ExchangeIconEntity

@Entity
data class ExchangeImageDataEntity(
    @PrimaryKey
    val id: String,
    val url: String
)

fun ExchangeImageDataEntity.toEntity(): ExchangeIconEntity {
    return ExchangeIconEntity(
        id = id,
        url = url
    )
}

fun ExchangeIconEntity.toDatabase(): ExchangeImageDataEntity {
    return ExchangeImageDataEntity(
        id = id,
        url = url
    )
}
