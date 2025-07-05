package com.app.pingu.core.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.pingu.ui.theme.regularBlack15
import com.app.pingu.ui.theme.regularLightGray15
import kotlinx.coroutines.delay

@Composable
fun ResendCodeTimer(
    initialTime: Int,
    onResendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var timeLeft by remember { mutableIntStateOf(initialTime) }
    var isCounting by remember { mutableStateOf(true) }

    LaunchedEffect(isCounting) {
        if (isCounting) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft -= 1
            }
            isCounting = false
        }
    }

    if (isCounting) {
        Text(
            text = "şu ka saniye sonra tekrar gönder",
            style = regularLightGray15,
            color = Color.Gray,
            modifier = modifier
        )
    } else {
        Text(
            text = "Tekrar gönder",
            style = regularBlack15,
            modifier = modifier.clickable {
                timeLeft = initialTime
                isCounting = true
                onResendClick()
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    ResendCodeTimer(
        initialTime = 120,
        onResendClick = {})
}
