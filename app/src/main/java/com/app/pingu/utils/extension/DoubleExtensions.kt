package com.app.pingu.utils.extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Double.toTurkishLira(): String {
    val symbols = DecimalFormatSymbols(Locale("tr", "TR")).apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }
    val formatter = DecimalFormat("#,##0.00", symbols)
    return "${formatter.format(this)} ₺"
}

fun Double?.orZero(): Double = this ?: 0.0