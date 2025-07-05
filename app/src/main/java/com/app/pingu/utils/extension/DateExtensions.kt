package com.app.pingu.utils.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.formatTransactionDate(): String {
    return this.split("T").getOrNull(0)?.replace("-", ".").orEmpty()
}

fun String.formatTransactionTime(): String {
    return this.split("T").getOrNull(1)?.take(5).orEmpty()
}

fun Date.toFormattedDateToday(pattern: String = "dd.MM.yyyy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this)
}