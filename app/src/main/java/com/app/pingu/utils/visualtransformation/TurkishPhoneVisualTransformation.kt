package com.app.pingu.utils.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TurkishPhoneVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
        var out = ""

        for (i in trimmed.indices) {
            if (i == 3) out += " "
            if (i == 6) out += " "
            if (i == 8) out += " "
            out += trimmed[i]
        }

        return TransformedText(AnnotatedString(out), turkishPhoneOffsetTranslator)
    }

    private val turkishPhoneOffsetTranslator = object : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int =
            when (offset) {
                in 4..6 -> offset + 1
                in 7..8 -> offset + 2
                in 9..10 -> offset + 3
                else -> offset
            }

        override fun transformedToOriginal(offset: Int): Int =
            when (offset) {
                in 4..7 -> offset - 1
                in 8..10 -> offset - 2
                in 11..13 -> offset - 3
                else -> offset
            }
    }
}