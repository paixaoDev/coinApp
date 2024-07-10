package com.paixao.dev.mbtest.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun Number.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    numberFormat.minimumFractionDigits = 0
    return numberFormat.format(this).trim().replace("\u00A0", " ")
}

fun BigDecimal.toCurrencyMinor(): String {
    try {
        if (this > BigDecimal.ONE) {
            val interval = this.currencyInterval()
            val currency = this.toCurrency()
            val minorCurrency = currency.substring(0, currency.indexOf(","))
            return "$minorCurrency $interval"
        } else {
            return this.toCurrency()
        }
    } catch (e: Exception) {
        return this.toCurrency()
    }
}

fun BigDecimal.currencyInterval(): String {
    return if (this >= BigDecimal.valueOf(1000000000)) {
        "B"
    } else if (this >= BigDecimal.valueOf(1000000)) {
        "M"
    } else if (this >= BigDecimal.valueOf(1000)) {
        "K"
    } else {
        ""
    }
}