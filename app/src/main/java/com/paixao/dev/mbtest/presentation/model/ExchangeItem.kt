package com.paixao.dev.mbtest.presentation.model

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.extensions.toCurrencyMinor

data class ExchangeItem(
    val id: String,
    val name: String,
    val image: String?,
    val value: String,
    val fav: Boolean = false
)


fun ExchangeEntity.toModel(): ExchangeItem {
    return ExchangeItem(
        id = this.id,
        name = this.name,
        image = image,
        value = this.volumeDay.toCurrencyMinor()
    )
}