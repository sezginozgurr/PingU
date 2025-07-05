package com.app.pingu.utils.extension

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun buildBoldAnnotatedString(
    fullText: String,
    boldText: String
): AnnotatedString {
    val startIndex = fullText.indexOf(boldText)
    return buildAnnotatedString {
        append(fullText)
        if (startIndex >= 0) {
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
                start = startIndex,
                end = startIndex + boldText.length
            )
        }
    }
}