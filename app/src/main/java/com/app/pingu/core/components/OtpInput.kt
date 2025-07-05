package com.app.pingu.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.ui.theme.darkGray
import com.app.pingu.ui.theme.lightGray
import com.app.pingu.ui.theme.regularBlack24

private const val OTP_LENGTH = 6

@Composable
fun OtpInputFields(
    code: String,
    onCodeChange: (String) -> Unit
) {
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val localInput = remember { mutableStateListOf("", "", "", "", "", "") }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(code) {
        if (code.length <= OTP_LENGTH) {
            code.forEachIndexed { index, c ->
                localInput[index] = c.toString()
            }
            for (i in code.length until OTP_LENGTH) {
                localInput[i] = ""
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        localInput.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    when {
                        newValue.length == 1 && newValue.all { it.isDigit() } -> {
                            localInput[index] = newValue
                            onCodeChange(localInput.joinToString(""))

                            if (index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                keyboardController?.hide()
                            }
                        }

                        newValue.isEmpty() -> {
                            localInput[index] = ""
                            onCodeChange(localInput.joinToString(""))

                            if (index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .width(48.dp)
                    .height(64.dp)
                    .focusRequester(focusRequesters[index])
                    .clickable {
                        focusRequesters[index].requestFocus()
                    },
                textStyle = regularBlack24.copy(
                    textAlign = TextAlign.Center
                ),
                visualTransformation = AsteriskVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = lightGray,
                    focusedBorderColor = darkGray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OtpInputFieldsPreview() {
    OtpInputFields(code = "123456") { }
}

