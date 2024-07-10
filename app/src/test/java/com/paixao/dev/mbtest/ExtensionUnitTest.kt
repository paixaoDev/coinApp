package com.paixao.dev.mbtest

import com.paixao.dev.mbtest.extensions.currencyInterval
import com.paixao.dev.mbtest.extensions.toCurrency
import com.paixao.dev.mbtest.extensions.toCurrencyMinor
import org.junit.Assert.assertEquals
import java.math.BigDecimal
import kotlin.test.Test

class ExtensionUnitTest {

    @Test
    fun `toCurrency should format number as currency`() {
        assertEquals("$1", 1.toCurrency())
        assertEquals("$1.23", 1.23.toCurrency())
        assertEquals("$1,234", 1234.toCurrency())
        assertEquals("$1,234.56", 1234.56.toCurrency())
    }

    @Test
    fun `toCurrencyMinor should format big decimal as minor currency`() {
        assertEquals("$1", BigDecimal("1").toCurrencyMinor())
        assertEquals("$1.23", BigDecimal("1.23").toCurrencyMinor())
        assertEquals("$1 K", BigDecimal("1234").toCurrencyMinor())
        assertEquals("$1 K", BigDecimal("1234.56").toCurrencyMinor())
        assertEquals("$1 M", BigDecimal("1000000").toCurrencyMinor())
        assertEquals("$1 B", BigDecimal("1000000000").toCurrencyMinor())
        assertEquals("$1 K", BigDecimal("1000").toCurrencyMinor())
    }

    @Test
    fun `currencyInterval should return correct interval`() {
        assertEquals("", BigDecimal("999").currencyInterval())
        assertEquals("K", BigDecimal("1000").currencyInterval())
        assertEquals("K", BigDecimal("1001").currencyInterval())
        assertEquals("M", BigDecimal("1000000").currencyInterval())
        assertEquals("B", BigDecimal("1000000000").currencyInterval())
    }
}