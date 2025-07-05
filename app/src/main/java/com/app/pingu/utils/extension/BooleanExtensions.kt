package com.app.pingu.utils.extension

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true