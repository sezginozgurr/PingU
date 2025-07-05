package com.app.pingu.presentation.result

enum class SoftPosErrorType(val code: Int) {
    ACTIVATION_NOT_FOUND(1),
    TERMINAL_NOT_FOUND(2),
    PAYMENT_DONE(3),
    INVALID_CALLBACK(4),
    CANCEL_PAYMENT(5),
    USER_HASH_NOT_FOUND(6),
    UNKNOWN(-1)
}