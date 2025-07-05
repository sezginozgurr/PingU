package com.app.pingu.core.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.app.pingu.ui.theme.boldBlack24
import com.app.pingu.ui.theme.regularBlack16Alpha50
import com.app.pingu.ui.theme.white

@Composable
fun SitAlertDialog(
    title: String,
    description: String,
    primaryButtonText: String,
    secondaryButtonText: String = "",
    dismissOnClickOutside: Boolean = true,
    primaryButtonAction: () -> Unit,
    secondaryButtonAction: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    AlertDialog(
        shape = RoundedCornerShape(16.dp),
        containerColor = white,
        properties = DialogProperties(dismissOnClickOutside = dismissOnClickOutside),
        title = {
            Text(
                text = title,
                style = boldBlack24,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = description,
                style = regularBlack16Alpha50,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            PrimaryButton(
                text = primaryButtonText,
                actionClickListener = primaryButtonAction
            )
        },
        dismissButton = {
            if (secondaryButtonText.isNotEmpty()) {
                SecondaryButton(
                    text = secondaryButtonText,
                    actionClickListener = secondaryButtonAction
                )
            }
        },
        onDismissRequest = onDismissRequest,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SitAlertDialog(
        title = "et",
        description = "imperdiet",
        primaryButtonText = "veritus",
        secondaryButtonText = "quisque",
        primaryButtonAction = {},
        secondaryButtonAction = {},
        onDismissRequest = {}
    )
}

