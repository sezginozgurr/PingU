package com.app.pingu.utils.extension

fun String.isNumeric(): Boolean {
    return this.all { it.isDigit() }
}

fun String.toResendCodeTimerFormatted(timeLeft: Int): String {
    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    return String.format(this, minutes, seconds)
}

fun String.removeLeadingZeros(): String {
    return this.dropWhile { it == '0' }
}

fun String.removeCountryCode90(): String {
    return this.replaceFirst("^90".toRegex(), "")
}