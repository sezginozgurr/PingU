package com.app.pingu.utils.extension

import kotlinx.serialization.json.Json

inline fun <reified T> T.toJson(): String {
    return Json.encodeToString(this)
}

inline fun <reified T> String.fromJson(): T {
    return Json.decodeFromString(this)
}