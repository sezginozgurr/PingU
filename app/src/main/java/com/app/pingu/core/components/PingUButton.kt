package com.app.pingu.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.R
import com.app.pingu.ui.theme.black
import com.app.pingu.ui.theme.darkGray
import com.app.pingu.ui.theme.mediumBlack16
import com.app.pingu.ui.theme.mediumWhite16
import com.app.pingu.ui.theme.silver
import com.app.pingu.ui.theme.whisper
import com.app.pingu.ui.theme.white

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Painter? = null,
    actionClickListener: (() -> Unit),
) {
    Button(
        onClick = { actionClickListener.invoke() },
        shape = RoundedCornerShape(12.dp),
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = black,
            disabledContainerColor = silver
        ),
        border = if (enabled) null else BorderStroke(1.dp, darkGray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (icon != null) Arrangement.SpaceBetween else Arrangement.Center
        ) {
            Text(
                text = text,
                style = mediumWhite16
            )
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = white
                )
            }
        }
    }
}


@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    actionClickListener: (() -> Unit)
) {
    OutlinedButton(
        onClick = { actionClickListener.invoke() },
        border = BorderStroke(1.dp, whisper),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = white)
    ) {
        Text(text = text, style = mediumBlack16)
    }
}

@Composable
fun IconTextButton(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    actionClickListener: (() -> Unit)
) {
    OutlinedButton(
        onClick = { actionClickListener.invoke() },
        modifier = modifier
            .defaultMinSize(minHeight = 50.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, whisper),
        colors = ButtonDefaults.buttonColors(containerColor = white)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = mediumBlack16,
            )

            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                tint = black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column {
        PrimaryButton(
            text = "Giriş Yap",
            actionClickListener = {}
        )
        Spacer(modifier = Modifier.height(2.dp))
        PrimaryButton(
            text = "Giriş Yap",
            enabled = false,
            actionClickListener = {}
        )
        Spacer(modifier = Modifier.height(2.dp))
        SecondaryButton(
            text = "İşlemi İptal Et",
            actionClickListener = {}
        )
        IconTextButton(
            text = "İşlemler",
            icon = painterResource(id = R.drawable.ic_launcher_background),
            actionClickListener = {}
        )
    }
}